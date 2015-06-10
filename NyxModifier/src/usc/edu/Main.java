package usc.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.Type;

public class Main {
    private static boolean isInvoke(InstructionHandle ih)
    {
        if(ih.getInstruction().getOpcode()<=0xba&&ih.getInstruction().getOpcode()>=0xb6)
            return true;
        return false;
    }
    private static boolean ToInstrument(String classname,String methodname, String sig)
    {
        if(!classname.equals("javax.servlet.jsp.JspWriter"))
            return false;
        if(!(methodname.equals("print") || methodname.equals("println") || methodname.equals("write")))
            return false;
        if(!sig.equals("(Ljava/lang/String;)V"))
            return false;
        return true;

    }
    public static Method instrument(MethodGen m)
    {
        InstructionList inslist=m.getInstructionList();
        InstructionHandle[] ihs=inslist.getInstructionHandles();
        InstructionFactory inf=new InstructionFactory(m.getConstantPool());
        for(int i=0;i<ihs.length;i++)
        {
            if(isInvoke(ihs[i]))
            {
                InvokeInstruction invoke=(InvokeInstruction)ihs[i].getInstruction();
                String sig=invoke.getSignature(m.getConstantPool());
                String methodname=invoke.getMethodName(m.getConstantPool());
                String classname=invoke.getReferenceType(m.getConstantPool()).toString();
                if(ToInstrument(classname,methodname,sig))
                {

                    Instruction replace=inf.createInvoke("usc.edu.Hooker.Hooker", "replace", Type.STRING, new Type[] {Type.STRING}, Constants.INVOKESTATIC);
                    inslist.insert(ihs[i],replace);
                    //System.out.println(replace);

                }
                //ivs.push(ihs[i]);
            }
        }
        inslist.setPositions();
        m.setInstructionList(inslist);
        m.setMaxStack();
        m.setMaxLocals();
        return m.getMethod();
    }
    public static void main(String argv[]) throws ClassFormatException, IOException
    {
        if(argv.length!=1)
        {
            System.out.println("Usgage: Instrumenter path");
            return;
        }
        String path=argv[0];
        List<String> classes=get_all_classes(path);
        Iterator<String> ir=classes.iterator();
        while(ir.hasNext())
        {
            String filepath=ir.next();
            JavaClass jcls = new ClassParser(filepath).parse();
            ClassGen cgen = new ClassGen(jcls);
            for (Method mthd : jcls.getMethods())
            {
                MethodGen method = new MethodGen(mthd, cgen.getClassName(), cgen.getConstantPool());
                cgen.replaceMethod(mthd,instrument(method));
            }

            File out_file = new File("GeneratedClasses/"+filepath.replaceFirst(path,""));
            File ofp = out_file.getParentFile();
            if (!ofp.exists()) {
                ofp.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(out_file);
            cgen.getJavaClass().dump(fos);
            fos.close();
        }
    }
    private static List<String> get_all_classes(String cls_dir) {
        List<String> all_cls = new ArrayList<String>();

        try {
            Process proc = Runtime.getRuntime().exec("find " + cls_dir + " -name *.class");
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()), 16);
            String line;
            while ((line = br.readLine()) != null) {
                all_cls.add(line.trim());
            }
            proc.waitFor();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return all_cls;
    }
}