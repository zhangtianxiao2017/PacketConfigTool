package com.test.config;


import com.test.config.dev.dao.FullPathElementDefDao;

import com.test.config.test.TbalePaneTest;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StartApp implements CommandLineRunner {

    @Autowired
    private TbalePaneTest tbalePaneTest;

    @Autowired
    private FullPathElementDefDao fullPathElementDefDao;

    public static void main(String[] args) {

        try
        {
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
            e.printStackTrace();
        }

        SpringApplication.run(StartApp.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        tbalePaneTest.startTablePane();
    }
}
