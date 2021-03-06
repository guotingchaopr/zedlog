package net.zeddev.zedlog.logger.impl;
/* Copyright (C) 2013  Zachary Scott <zscott.dev@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import net.zeddev.zedlog.logger.AbstractDataLogger;
import net.zeddev.zedlog.logger.LogEntry;
import net.zeddev.zedlog.logger.impl.event.MouseMovedEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionListener;

/**
 * A {@code DataLogger} for mouse movements events.
 *
 * @author Zachary Scott <zscott.dev@gmail.com>
 */
public final class MouseMovementLogger extends AbstractDataLogger
		implements NativeMouseMotionListener {

	/** Creates a new {@code MouseMovementLogger}. */
	public MouseMovementLogger() {
		super();
		GlobalScreen.getInstance().addNativeMouseMotionListener(this);
	}

	@Override
	public void shutdown() {
		super.shutdown();
		GlobalScreen.getInstance().removeNativeMouseMotionListener(this);
	}

	@Override
	public String type() {
		return "MouseMovement";
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent event) {

		MouseMovedEvent moveEvent = new MouseMovedEvent(event);

		LogEntry logEntry = new LogEntry(this, moveEvent.toString(), moveEvent);
		notifyDataLoggerObservers(this, logEntry);

	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent nme) {
		// IGNORED
	}

}

