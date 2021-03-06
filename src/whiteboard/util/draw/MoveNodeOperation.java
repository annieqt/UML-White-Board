/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package whiteboard.util.draw;

import java.awt.geom.Point2D;
import javax.swing.undo.AbstractUndoableEdit;
import util.Command;

/**
 * This class implements a single undoable operation to move a node.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class MoveNodeOperation extends AbstractUndoableEdit
implements Command {

  private Node node;
  private CompositeNode newParent, originalParent;
  private Point2D targetPos;
  private Point2D originalPos;

  /**
   * Constructor.
   * @param aNode the node
   * @param aNewParent the new parent
   * @param aTargetPos the target position
   */
  public MoveNodeOperation(Node aNode, CompositeNode aNewParent,
    Point2D aTargetPos) {
    node = aNode;
    newParent = aNewParent;
    targetPos = aTargetPos;
    originalParent = node.getParent();
    originalPos = new Point2D.Double(node.getAbsoluteX1(),
      node.getAbsoluteY1());
  }

  /**
   * {@inheritDoc}
   */
  public void run() {
    if (originalParent != null) originalParent.removeChild(node);
    if (newParent != null) newParent.addChild(node);
    node.setAbsolutePos(targetPos.getX(), targetPos.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void undo() {
    newParent.removeChild(node);
    originalParent.addChild(node);
    node.setAbsolutePos(originalPos.getX(), originalPos.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void redo() {
    run();
  }
}
