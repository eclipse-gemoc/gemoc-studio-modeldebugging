/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.dsl.debug.ide.ui.provider;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

/**
 * A {@link CompositeImageDescriptor} that overlay an {@link Image} on top of a base {@link Image}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class OverlayImageDescriptor extends CompositeImageDescriptor {

	/**
	 * Base {@link Image}.
	 */
	private final Image image;

	/**
	 * Overlay {@link Image}.
	 */
	private final Image overlay;

	/**
	 * The size of the {@link OverlayImageDescriptor#image}.
	 */
	private final Point size;

	/**
	 * Constructor.
	 * 
	 * @param image
	 *            the base {@link Image}
	 * @param overlay
	 *            the overlay {@link Image}
	 */
	public OverlayImageDescriptor(Image image, Image overlay) {
		this.image = image;
		final Rectangle bounds = image.getBounds();
		this.size = new Point(bounds.width, bounds.height);
		this.overlay = overlay;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.resource.CompositeImageDescriptor#drawCompositeImage(int, int)
	 */
	protected void drawCompositeImage(int arg0, int arg1) {
		drawImage(image.getImageData(), 0, 0);
		ImageData overlayImageData = overlay.getImageData();
		drawImage(overlayImageData, 0, 0);
	}

	protected Point getSize() {
		return size;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof OverlayImageDescriptor && image.equals(((OverlayImageDescriptor)obj).image)
				&& overlay.equals(((OverlayImageDescriptor)obj).overlay);
	}

	@Override
	public int hashCode() {
		return image.hashCode() ^ overlay.hashCode();
	}

}
