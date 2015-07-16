package sql.usc.Soot;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.options.Options;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by mianwan on 7/14/15.
 */
public class AndroidApp {
    private Map<String, SootClass> allClasses = new HashMap<String, SootClass>();

    public AndroidApp(String androidJarPath, String apkPath, String classList) {
        Options.v().set_src_prec(Options.src_prec_apk);
        Options.v().set_android_jars(androidJarPath);
        Options.v().set_whole_program(true);
        Options.v().set_verbose(false);
        Options.v().set_keep_line_number(true);
        Options.v().set_keep_offset(true);
        Options.v().set_allow_phantom_refs(true);
        List<String> dirList = new ArrayList<String>();
        dirList.add(apkPath);
        Options.v().set_process_dir(dirList);

        List<SootMethod> entryPoints = new ArrayList<SootMethod>();
        Set<SootMethod> allMethods = new HashSet<SootMethod>();


        try {
            BufferedReader br = new BufferedReader(new FileReader(classList));
            String className;
            while (null != (className = br.readLine())) {
                SootClass sc = Scene.v().loadClassAndSupport(className);
//                Scene.v().loadNecessaryClasses();
                // Ignore android support classes
                if (!sc.getName().startsWith("android.support")) {
                    sc.setApplicationClass();
//                    allClasses.add(sc);
                    allClasses.put(className, sc);
                    allMethods.addAll(sc.getMethods());
                    try {
                        SootMethod onCreate = sc.getMethodByName("onCreate");
                        if (onCreate.isConcrete()) {
                            entryPoints.add(onCreate);
                            System.out.println(onCreate);
                        }

                    } catch (RuntimeException e) {
//                       System.out.println(className + "doesn't have the onCreate() method!");
                    }

                    try {
                        SootMethod doInBackground = sc.getMethodByName("doInBackground");
                        if (doInBackground.isConcrete()) {
                            entryPoints.add(doInBackground);
                            System.out.println(doInBackground);
                        }

                    } catch (RuntimeException e) {
//                       System.out.println(className + "doesn't have the onCreate() method!");
                    }


                }

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        Scene.v().loadNecessaryClasses();
//        Scene.v().setEntryPoints(entryPoints);
//        CHATransformer.v().transform();
//        CallGraph cg = Scene.v().getCallGraph();

    }

    public Map<String, SootClass> getAllClasses() {
        return allClasses;
    }
}
