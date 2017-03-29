package com.ogatism.intellij.plugin.timeconverter;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by WEBSYSTEM16 on 2017/03/17.
 */
public class TimeConverterAction extends AnAction {
    private AnActionEvent event;
    private TimeConverterView view = new TimeConverterView(this);

    @Override
    public void actionPerformed(AnActionEvent e) {
        this.event = e;

        view.start(e.getProject());

        System.out.println(System.currentTimeMillis() / 1000L);
    }
}
