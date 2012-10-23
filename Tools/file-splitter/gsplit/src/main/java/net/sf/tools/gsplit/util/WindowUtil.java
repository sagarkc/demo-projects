/**
 * 
 */
package net.sf.tools.gsplit.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public final class WindowUtil {

	/**
	 * This method changes the location of the component to move it to the
	 * center of the screen.
	 * 
	 * @param component
	 */
	public static void bringToCenter(final Component component) {
		Dimension originalSize = component.getSize();
		Dimension scrResolution = Toolkit.getDefaultToolkit().getScreenSize();
		Point location = new Point(
				(int) ((scrResolution.getWidth() / 2) - (originalSize
						.getWidth() / 2)),
				(int) ((scrResolution.getHeight() / 2) - (originalSize
						.getHeight() / 2)));
		component.setLocation(location);
	}

	/**
	 * Changes the location of the child component according to the parent
	 * component to make the child component center to the parent component. <br>
	 * <i>Note: </i>The component can be a Frame or Dialog.
	 * 
	 * @param child
	 * @param parent
	 */
	public static void bringCenterTo(final Component child,
			final Component parent) {
		Dimension childOriginalSize = child.getSize();
		Dimension parentSize = parent.getSize();
		Point parentLocation = parent.getLocation();
		Point location = new Point(
				parentLocation.x
						+ (parentSize.width / 2 - childOriginalSize.width / 2),
				parentLocation.y
						+ (parentSize.height / 2 - childOriginalSize.height / 2));
		child.setLocation(location);
	}

	/**
	 * Enables/Disables all the components based on the input.
	 * 
	 * @param components
	 */
	public static void enableComponents(final boolean enable,
			final Component... components) {
		if (null == components)
			return;
		if (components.length <= 0)
			return;
		for (Component component : components) {
			if (null != component) {
				component.setEnabled(enable);
			}
		}
	}

	/**
	 * Enables/Disables all the components of a Frame based on the input.
	 * 
	 * @param enable
	 * @param parent
	 */
	public static void enableAllComponents(final boolean enable,
			final Frame parent) {
		if (null != parent) {
			Component[] components = parent.getComponents();
			if (null == components)
				return;
			if (components.length <= 0)
				return;
			for (Component component : components) {
				if (null != component) {
					component.setEnabled(enable);
				}
			}
		}
	}

	/**
	 * Enables/Disables all the components of a Frame except the specified
	 * components based on the input.
	 * 
	 * @param enable
	 * @param parent
	 * @param components
	 */
	public static void enableAllComponentsExcept(final boolean enable,
			final Frame parent, final Component... components) {
		if (null != parent) {
			Component[] allComponents = parent.getComponents();
			if (null == allComponents)
				return;
			if (allComponents.length <= 0)
				return;
			for (Component component : allComponents) {
				if (null != component && components != null) {
					if (CollectionUtils.contains(component, components)) {
						continue;
					}
					component.setEnabled(enable);
				}
			}
		}
	}

	public static void visibleComponents(final boolean visible,
			final Component... components) {
		if (null == components)
			return;
		if (components.length <= 0)
			return;
		for (Component component : components) {
			if (null != component) {
				component.setVisible(visible);
			}
		}
	}

	public static void visibleAllComponents(final boolean visible,
			final Frame parent) {
		if (null != parent) {
			Component[] components = parent.getComponents();
			if (null == components)
				return;
			if (components.length <= 0)
				return;
			for (Component component : components) {
				if (null != component) {
					component.setVisible(visible);
				}
			}
		}
	}

	public static void visibleAllComponentsExcept(final boolean visible,
			final Frame parent, final Component... components) {
		if (null != parent) {
			Component[] allComponents = parent.getComponents();
			if (null == allComponents)
				return;
			if (allComponents.length <= 0)
				return;
			for (Component component : allComponents) {
				if (null != component && components != null) {
					if (CollectionUtils.contains(component, components)) {
						continue;
					}
					component.setVisible(visible);
				}
			}
		}
	}

	/**
	 * Enables/Disables all the components of a Frame based on the input.
	 * 
	 * @param enable
	 * @param parent
	 */
	public static void enableAllComponents(final boolean enable,
			final JDialog parent) {
		if (null != parent) {
			Component[] components = parent.getRootPane().getComponents();
			if (null == components)
				return;
			if (components.length <= 0)
				return;
			for (Component component : components) {
				if (null != component) {
					component.setEnabled(enable);
				}
			}
		}
	}

	/**
	 * Enables/Disables all the components of a Frame except the specified
	 * components based on the input.
	 * 
	 * @param enable
	 * @param parent
	 * @param components
	 */
	public static void enableAllComponentsExcept(final boolean enable,
			final JDialog parent, final Component... components) {
		if (null != parent) {
			Component[] allComponents = parent.getRootPane().getComponents();
			if (null == allComponents)
				return;
			if (allComponents.length <= 0)
				return;
			for (Component component : allComponents) {
				if (null != component && components != null) {
					if (CollectionUtils.contains(component, components)) {
						continue;
					}
					component.setEnabled(enable);
				}
			}
		}
	}

}
