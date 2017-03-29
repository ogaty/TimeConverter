package com.ogatism.intellij.plugin.timeconverter;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import org.intellij.lang.annotations.JdkConstants;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by WEBSYSTEM16 on 2017/03/21.
 */
public class TimeConverterView implements ActionListener {
    private TimeConverterAction action;
    public TimeConverterView(TimeConverterAction action) {
        this.action = action;
    }

    private JButton up = new JButton("↑");
    private JButton down = new JButton("↓");
    private JButton current = new JButton("現在");

    private JTextField unix = new JTextField(String.valueOf((System.currentTimeMillis() / 1000L)));

    private JTextField year = new JTextField();
    private JTextField month = new JTextField();
    private JTextField day = new JTextField();

    private JTextField hour = new JTextField();
    private JTextField minute = new JTextField();
    private JTextField second = new JTextField();

    public void start(Project p) {
        DialogBuilder d = new DialogBuilder(p);
        d.setTitle("TimeConverter");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        current.addActionListener(this);
        current.setAlignmentX(0.5f);
        panel.add(current);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(unix);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel updownPanel = new JPanel();
        updownPanel.setLayout(new BoxLayout(updownPanel, BoxLayout.X_AXIS));
        panel.add(Box.createRigidArea(new Dimension(10, 0)));

        up.addActionListener(this);
        updownPanel.add(up);

        down.addActionListener(this);
        updownPanel.add(down);
        panel.add(updownPanel);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));

        year.setPreferredSize(new Dimension(130, 50));
        datePanel.add(year);

        JLabel yearLabel = new JLabel("年");
        datePanel.add(yearLabel);

        month.setPreferredSize(new Dimension(130, 50));
        datePanel.add(month);

        JLabel monthLabel = new JLabel("月");
        datePanel.add(monthLabel);

        day.setPreferredSize(new Dimension(130, 50));
        datePanel.add(day);

        JLabel dayLabel = new JLabel("日");
        datePanel.add(dayLabel);

        hour.setPreferredSize(new Dimension(130, 50));
        datePanel.add(hour);

        JLabel hourLabel = new JLabel("時");
        datePanel.add(hourLabel);

        minute.setPreferredSize(new Dimension(130, 50));
        datePanel.add(minute);

        JLabel minuteLabel = new JLabel("分");
        datePanel.add(minuteLabel);

        second.setPreferredSize(new Dimension(130, 50));
        datePanel.add(second);

        JLabel secondLabel = new JLabel("秒");
        datePanel.add(secondLabel);

        panel.add(datePanel);
        panel.setBackground(UIManager.getColor("DialogWrapper.southPanelBackground"));

        d.centerPanel(panel);
        d.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Calendar datetime = Calendar.getInstance();
        if (e.getSource() == up) {
            datetime.set(Calendar.YEAR, Integer.parseInt(year.getText()));
            datetime.set(Calendar.MONTH, Integer.parseInt(month.getText()) - 1);
            datetime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day.getText()));
            datetime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour.getText()));
            datetime.set(Calendar.MINUTE, Integer.parseInt(minute.getText()));
            datetime.set(Calendar.SECOND, Integer.parseInt(second.getText()));
            datetime.set(Calendar.MILLISECOND, 0);
            unix.setText(String.valueOf(datetime.getTimeInMillis() / 1000L));
        } else if (e.getSource() == down) {
            datetime.setTime(new Date(Long.parseLong(unix.getText()) * 1000L));
            year.setText(String.valueOf(datetime.get(Calendar.YEAR)));
            month.setText(String.valueOf(datetime.get(Calendar.MONTH) + 1));
            day.setText(String.valueOf(datetime.get(Calendar.DAY_OF_MONTH)));
            hour.setText(String.valueOf(datetime.get(Calendar.HOUR_OF_DAY)));
            minute.setText(String.valueOf(datetime.get(Calendar.MINUTE)));
            second.setText(String.valueOf(datetime.get(Calendar.SECOND)));
        } else if (e.getSource() == current) {
            datetime.setTime(new Date());
            unix.setText(String.valueOf((System.currentTimeMillis() / 1000L)));
            year.setText(String.valueOf(datetime.get(Calendar.YEAR)));
            month.setText(String.valueOf(datetime.get(Calendar.MONTH) + 1));
            day.setText(String.valueOf(datetime.get(Calendar.DAY_OF_MONTH)));
            hour.setText(String.valueOf(datetime.get(Calendar.HOUR_OF_DAY)));
            minute.setText(String.valueOf(datetime.get(Calendar.MINUTE)));
            second.setText(String.valueOf(datetime.get(Calendar.SECOND)));
        }
    }
}
