package org.jcommunique.demos;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JCommuniqueDemoLauncher {
	
	public static void main(String[] args) {
		JFrame selector = new JFrame("JCommunique demo launcher");
		selector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selector.setSize(480, 270);
		selector.setLocationRelativeTo(null);
		selector.setLayout(new GridLayout(2, 2));
		
		selector.add(getLaunchButton(CustomNotificationDemo.class, args));
		selector.add(getLaunchButton(QueueManagerDemo.class, args));
		selector.add(getLaunchButton(SequenceManagerDemo.class, args));
		selector.add(getLaunchButton(SimpleManagerDemo.class, args));
		
		selector.setVisible(true);
	}

	private static Component getLaunchButton(final Class<?> demoClass, final String[] args) {
		JButton butt = new JButton(demoClass.getSimpleName().substring(0, demoClass.getSimpleName().length()-4));
		
		final Method main;
		try {
			main = demoClass.getDeclaredMethod("main", String[].class);
			main.setAccessible(true);
			if (!main.canAccess(null) || !Modifier.isStatic(main.getModifiers())) throw new Error("Invalid main method in "+demoClass.getName());
		} catch (NoSuchMethodException e) { throw new Error("Invalid demo: "+demoClass.getName(), e); }
		
		butt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				new Thread() {
					@Override
					public void run() {
						try {
							System.out.println("Starting demo "+demoClass.getName());
							main.invoke(null, new Object[]{args});
							System.out.println("Demo "+demoClass.getName()+" has finished");
						} catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
							throw new Error("Invalid demo, previous checks have failed", e);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		});
		
		return butt;
	}
	
}
