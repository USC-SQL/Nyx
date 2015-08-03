#! /bin/bash
rm -r hookerlib
cp -r /home/dingli/gitprojects/Nyx/hookerlib/ ./hookerlib
cd hookerlib
javac usc/edu/Hooker/Hooker.java
cd ..
java -jar NyxModifier.jar /home/dingli/gitprojects/Nyx/TestApps/bookstore/./WEB-INF/classes
cp -r hookerlib/* GeneratedClasses/
cp /home/dingli/gitprojects/Nyx/TestApps/bookstore/images/icon_shop.gif ./icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#ffffff' -opaque '#ffffff' icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#abdee6' -opaque '#abdee6' icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#c08c81' -opaque '#c08c81' icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#be8e84' -opaque '#be8e84' icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#2e7a8a' -opaque '#2e7a8a' icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#800000' -opaque '#800000' icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#ddbbbb' -opaque '#ddbbbb' icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#6d7172' -opaque '#6d7172' icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#e0e0e0' -opaque '#e0e0e0' icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#3d8ca2' -opaque '#3d8ca2' icon_shop.gif
convert  -fuzz 10% icon_shop.gif -fill '#a24444' -opaque '#a24444' icon_shop.gif
convert icon_shop.gif -fill '#000000' -opaque '#ffffff' icon_shop.gif
convert icon_shop.gif -fill '#301020' -opaque '#abdee6' icon_shop.gif
convert icon_shop.gif -fill '#005000' -opaque '#c08c81' icon_shop.gif
convert icon_shop.gif -fill '#406050' -opaque '#be8e84' icon_shop.gif
convert icon_shop.gif -fill '#d00080' -opaque '#2e7a8a' icon_shop.gif
convert icon_shop.gif -fill '#00d050' -opaque '#800000' icon_shop.gif
convert icon_shop.gif -fill '#303010' -opaque '#ddbbbb' icon_shop.gif
convert icon_shop.gif -fill '#5020e0' -opaque '#6d7172' icon_shop.gif
convert icon_shop.gif -fill '#001010' -opaque '#e0e0e0' icon_shop.gif
convert icon_shop.gif -fill '#b01020' -opaque '#3d8ca2' icon_shop.gif
convert icon_shop.gif -fill '#f04090' -opaque '#a24444' icon_shop.gif
cp /home/dingli/gitprojects/Nyx/TestApps/bookstore/images/icon_reg.gif ./icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#ffffff' -opaque '#ffffff' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#f3f3c9' -opaque '#f3f3c9' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#fff4bc' -opaque '#fff4bc' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#fefcfb' -opaque '#fefcfb' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#711111' -opaque '#711111' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#fcdfaf' -opaque '#fcdfaf' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#918e8c' -opaque '#918e8c' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#f5f5f5' -opaque '#f5f5f5' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#800000' -opaque '#800000' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#fbfbfb' -opaque '#fbfbfb' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#a3584b' -opaque '#a3584b' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#382520' -opaque '#382520' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#fadba9' -opaque '#fadba9' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#6b2215' -opaque '#6b2215' icon_reg.gif
convert  -fuzz 10% icon_reg.gif -fill '#f8f8f8' -opaque '#f8f8f8' icon_reg.gif
convert icon_reg.gif -fill '#000000' -opaque '#ffffff' icon_reg.gif
convert icon_reg.gif -fill '#203040' -opaque '#f3f3c9' icon_reg.gif
convert icon_reg.gif -fill '#404040' -opaque '#fff4bc' icon_reg.gif
convert icon_reg.gif -fill '#000000' -opaque '#fefcfb' icon_reg.gif
convert icon_reg.gif -fill '#70d060' -opaque '#711111' icon_reg.gif
convert icon_reg.gif -fill '#301020' -opaque '#fcdfaf' icon_reg.gif
convert icon_reg.gif -fill '#405020' -opaque '#918e8c' icon_reg.gif
convert icon_reg.gif -fill '#101010' -opaque '#f5f5f5' icon_reg.gif
convert icon_reg.gif -fill '#00d050' -opaque '#800000' icon_reg.gif
convert icon_reg.gif -fill '#000000' -opaque '#fbfbfb' icon_reg.gif
convert icon_reg.gif -fill '#509080' -opaque '#a3584b' icon_reg.gif
convert icon_reg.gif -fill '#d0d0a0' -opaque '#382520' icon_reg.gif
convert icon_reg.gif -fill '#303010' -opaque '#fadba9' icon_reg.gif
convert icon_reg.gif -fill '#10d070' -opaque '#6b2215' icon_reg.gif
convert icon_reg.gif -fill '#101010' -opaque '#f8f8f8' icon_reg.gif
cp /home/dingli/gitprojects/Nyx/TestApps/bookstore/images/icon_sign.gif ./icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#e6e6e6' -opaque '#e6e6e6' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#912222' -opaque '#912222' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#fbefef' -opaque '#fbefef' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#800000' -opaque '#800000' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#f3f3f3' -opaque '#f3f3f3' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#d1d1d1' -opaque '#d1d1d1' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#a24444' -opaque '#a24444' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#ffffff' -opaque '#ffffff' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#ddc1c4' -opaque '#ddc1c4' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#742d1f' -opaque '#742d1f' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#fdfdfd' -opaque '#fdfdfd' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#aaaaaa' -opaque '#aaaaaa' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#fcfcfc' -opaque '#fcfcfc' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#c48888' -opaque '#c48888' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#ddbbbb' -opaque '#ddbbbb' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#fbf4f4' -opaque '#fbf4f4' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#402420' -opaque '#402420' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#94685f' -opaque '#94685f' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#8d8d8d' -opaque '#8d8d8d' icon_sign.gif
convert  -fuzz 10% icon_sign.gif -fill '#000000' -opaque '#000000' icon_sign.gif
convert icon_sign.gif -fill '#100000' -opaque '#e6e6e6' icon_sign.gif
convert icon_sign.gif -fill '#c090f0' -opaque '#912222' icon_sign.gif
convert icon_sign.gif -fill '#100000' -opaque '#fbefef' icon_sign.gif
convert icon_sign.gif -fill '#00d050' -opaque '#800000' icon_sign.gif
convert icon_sign.gif -fill '#101010' -opaque '#f3f3f3' icon_sign.gif
convert icon_sign.gif -fill '#001020' -opaque '#d1d1d1' icon_sign.gif
convert icon_sign.gif -fill '#f04090' -opaque '#a24444' icon_sign.gif
convert icon_sign.gif -fill '#000000' -opaque '#ffffff' icon_sign.gif
convert icon_sign.gif -fill '#103030' -opaque '#ddc1c4' icon_sign.gif
convert icon_sign.gif -fill '#60c060' -opaque '#742d1f' icon_sign.gif
convert icon_sign.gif -fill '#000000' -opaque '#fdfdfd' icon_sign.gif
convert icon_sign.gif -fill '#300000' -opaque '#aaaaaa' icon_sign.gif
convert icon_sign.gif -fill '#000000' -opaque '#fcfcfc' icon_sign.gif
convert icon_sign.gif -fill '#503090' -opaque '#c48888' icon_sign.gif
convert icon_sign.gif -fill '#303010' -opaque '#ddbbbb' icon_sign.gif
convert icon_sign.gif -fill '#101010' -opaque '#fbf4f4' icon_sign.gif
convert icon_sign.gif -fill '#20e000' -opaque '#402420' icon_sign.gif
convert icon_sign.gif -fill '#8040d0' -opaque '#94685f' icon_sign.gif
convert icon_sign.gif -fill '#600060' -opaque '#8d8d8d' icon_sign.gif
convert icon_sign.gif -fill '#ffffff' -opaque '#000000' icon_sign.gif
cp /home/dingli/gitprojects/Nyx/TestApps/bookstore/images/icon_home.gif ./icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#f9f3f3' -opaque '#f9f3f3' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#563a15' -opaque '#563a15' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#e4e4e5' -opaque '#e4e4e5' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#800000' -opaque '#800000' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#212223' -opaque '#212223' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#dbbdc1' -opaque '#dbbdc1' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#a24444' -opaque '#a24444' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#3c3d3d' -opaque '#3c3d3d' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#ffffff' -opaque '#ffffff' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#390202' -opaque '#390202' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#c4c7c5' -opaque '#c4c7c5' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#bababa' -opaque '#bababa' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#7d7e7e' -opaque '#7d7e7e' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#703636' -opaque '#703636' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#010101' -opaque '#010101' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#646464' -opaque '#646464' icon_home.gif
convert  -fuzz 10% icon_home.gif -fill '#000000' -opaque '#000000' icon_home.gif
convert icon_home.gif -fill '#101010' -opaque '#f9f3f3' icon_home.gif
convert icon_home.gif -fill '#c0b050' -opaque '#563a15' icon_home.gif
convert icon_home.gif -fill '#100000' -opaque '#e4e4e5' icon_home.gif
convert icon_home.gif -fill '#00d050' -opaque '#800000' icon_home.gif
convert icon_home.gif -fill '#b0e040' -opaque '#212223' icon_home.gif
convert icon_home.gif -fill '#301020' -opaque '#dbbdc1' icon_home.gif
convert icon_home.gif -fill '#f04090' -opaque '#a24444' icon_home.gif
convert icon_home.gif -fill '#40c050' -opaque '#3c3d3d' icon_home.gif
convert icon_home.gif -fill '#000000' -opaque '#ffffff' icon_home.gif
convert icon_home.gif -fill '#00ffff' -opaque '#390202' icon_home.gif
convert icon_home.gif -fill '#302020' -opaque '#c4c7c5' icon_home.gif
convert icon_home.gif -fill '#302030' -opaque '#bababa' icon_home.gif
convert icon_home.gif -fill '#903050' -opaque '#7d7e7e' icon_home.gif
convert icon_home.gif -fill '#d0a050' -opaque '#703636' icon_home.gif
convert icon_home.gif -fill '#fffff0' -opaque '#010101' icon_home.gif
convert icon_home.gif -fill '#608010' -opaque '#646464' icon_home.gif
convert icon_home.gif -fill '#ffffff' -opaque '#000000' icon_home.gif
cp /home/dingli/gitprojects/Nyx/TestApps/bookstore/images/Logo_bookstore.gif ./Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#136aa4' -opaque '#136aa4' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#f5f8f9' -opaque '#f5f8f9' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#f8eeee' -opaque '#f8eeee' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#c48989' -opaque '#c48989' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#800000' -opaque '#800000' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#062e4b' -opaque '#062e4b' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#055c96' -opaque '#055c96' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#054067' -opaque '#054067' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#ffffff' -opaque '#ffffff' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#09161f' -opaque '#09161f' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#6e7fe9' -opaque '#6e7fe9' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#c5fcff' -opaque '#c5fcff' Logo_bookstore.gif
convert  -fuzz 10% Logo_bookstore.gif -fill '#044f81' -opaque '#044f81' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#e030a0' -opaque '#136aa4' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#101010' -opaque '#f5f8f9' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#100000' -opaque '#f8eeee' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#605000' -opaque '#c48989' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#00d050' -opaque '#800000' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#00e010' -opaque '#062e4b' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#70a050' -opaque '#055c96' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#90c0a0' -opaque '#054067' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#000000' -opaque '#ffffff' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#e0f0d0' -opaque '#09161f' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#5060c0' -opaque '#6e7fe9' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#302000' -opaque '#c5fcff' Logo_bookstore.gif
convert Logo_bookstore.gif -fill '#70b070' -opaque '#044f81' Logo_bookstore.gif
cp /home/dingli/gitprojects/Nyx/TestApps/bookstore/images/icon_admin.gif ./icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#ffffff' -opaque '#ffffff' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#2de029' -opaque '#2de029' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#785c56' -opaque '#785c56' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#b0afaf' -opaque '#b0afaf' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#78534c' -opaque '#78534c' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#62342b' -opaque '#62342b' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#fcfcfc' -opaque '#fcfcfc' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#800000' -opaque '#800000' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#ddbbbb' -opaque '#ddbbbb' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#993333' -opaque '#993333' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#bcbcbc' -opaque '#bcbcbc' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#99aa87' -opaque '#99aa87' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#e0e0e0' -opaque '#e0e0e0' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#881111' -opaque '#881111' icon_admin.gif
convert  -fuzz 10% icon_admin.gif -fill '#020802' -opaque '#020802' icon_admin.gif
convert icon_admin.gif -fill '#000000' -opaque '#ffffff' icon_admin.gif
convert icon_admin.gif -fill '#902060' -opaque '#2de029' icon_admin.gif
convert icon_admin.gif -fill '#7060f0' -opaque '#785c56' icon_admin.gif
convert icon_admin.gif -fill '#201030' -opaque '#b0afaf' icon_admin.gif
convert icon_admin.gif -fill '#f000b0' -opaque '#78534c' icon_admin.gif
convert icon_admin.gif -fill '#c0a0f0' -opaque '#62342b' icon_admin.gif
convert icon_admin.gif -fill '#000000' -opaque '#fcfcfc' icon_admin.gif
convert icon_admin.gif -fill '#00d050' -opaque '#800000' icon_admin.gif
convert icon_admin.gif -fill '#303010' -opaque '#ddbbbb' icon_admin.gif
convert icon_admin.gif -fill '#e08050' -opaque '#993333' icon_admin.gif
convert icon_admin.gif -fill '#303020' -opaque '#bcbcbc' icon_admin.gif
convert icon_admin.gif -fill '#002070' -opaque '#99aa87' icon_admin.gif
convert icon_admin.gif -fill '#001010' -opaque '#e0e0e0' icon_admin.gif
convert icon_admin.gif -fill '#30c0f0' -opaque '#881111' icon_admin.gif
convert icon_admin.gif -fill '#e0ffff' -opaque '#020802' icon_admin.gif
