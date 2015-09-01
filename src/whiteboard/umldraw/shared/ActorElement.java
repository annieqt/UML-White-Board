package whiteboard.umldraw.shared;

import java.awt.Color;

import whiteboard.util.draw.AbstractCompositeNode;
import whiteboard.util.draw.CustomLabel;
import whiteboard.util.draw.Defaults;
import whiteboard.util.draw.DrawingContext;
import whiteboard.util.draw.Label;
import whiteboard.util.draw.LabelSource;
import whiteboard.util.model.RelationEndType;
import whiteboard.util.model.RelationType;
import whiteboard.util.model.UmlModelElement;

/**
 * This class represents a Note element in the UML diagram. This is in general
 * a multiline element which is flexible in resizing.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public final class ActorElement extends AbstractCompositeNode
implements UmlNode, LabelSource {

  private static final long serialVersionUID = -4403380938254940682L;
  private static final int CORNER_SIZE = 10;
  private static final double MARGIN_TOP = CORNER_SIZE + 2;
  private static final Color FILL_COLOR = new Color(249, 249, 145);
  private String content;
 // private Label label = new SimpleLabel();
  private CustomLabel label = new CustomLabel();
  private static ActorElement prototype = new ActorElement();

  /**
   * Returns the prototype instance.
   * @return the prototype instance
   */
  public static ActorElement getPrototype() { return prototype; }

  /**
   * Constructor.
   */
  private ActorElement() {
    setSize(180, 60);
    setLabelText("");
    label.setSource(this);
    label.setParent(this);
    label.setOrigin(Defaults.getInstance().getMarginLeft(), MARGIN_TOP);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object clone() {
	ActorElement cloned = (ActorElement) super.clone();
    cloned.label = (CustomLabel) label.clone();
    cloned.label.setSource(cloned);
    cloned.label.setParent(cloned);
    return cloned;
  }

  /**
   * {@inheritDoc}
   */
  public String getLabelText() { return content; }

  /**
   * {@inheritDoc}
   */
  public void setLabelText(String aText) { content = aText; }

  /**
   * {@inheritDoc}
   */
  @Override
  public void invalidate() { label.invalidate(); }

  /**
   * {@inheritDoc}
   */
  public Label getLabelAt(double mx, double my) {
    if (inInnerArea(mx, my)) return label;
    return null;
  }

  /**
   * Returns true if the specified point is in the inner Note area. It keeps
   * the margins from reacting to mouse clicks in order to improve usability.
   * @param mx the mapped mouse x position
   * @param my the mapped mouse y position
   * @return true if in label area, false otherwise
   */
  private boolean inInnerArea(double mx, double my) {
    return mx >= (getAbsoluteX1() + Defaults.getInstance().getMarginLeft()) &&
      mx <= (getAbsoluteX2() - Defaults.getInstance().getMarginRight()) &&
      my >= (getAbsoluteY1() + MARGIN_TOP) &&
      my <= (getAbsoluteY2() - Defaults.getInstance().getMarginBottom());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void draw(DrawingContext drawingContext) {
    double width = getSize().getWidth(), height = getSize().getHeight();
    double marginSide = Defaults.getInstance().getMarginSide();
    double marginBottom = Defaults.getInstance().getMarginBottom();
    if (!label.isValid()) {
      label.setSize(width - marginSide, height);
      label.recalculateSize(drawingContext);
      // Set a new height if it is the old one
      if ((label.getSize().getHeight() + MARGIN_TOP + marginBottom) > height) {
        height = label.getSize().getHeight() + (MARGIN_TOP + marginBottom);
        setSize(width, height);
      }
    }
    double x = getAbsoluteX1(), y = getAbsoluteY1();
    /*GeneralPath mainShape = new GeneralPath();
    mainShape.moveTo(x, y);
    mainShape.lineTo(x + width - CORNER_SIZE, y);
    mainShape.lineTo(x + width, y + CORNER_SIZE);
    mainShape.lineTo(x + width, y + height);
    mainShape.lineTo(x, y + height);
    mainShape.closePath();
    GeneralPath corner = new GeneralPath();
    corner.moveTo(x + width - CORNER_SIZE, y);
    corner.lineTo(x + width - CORNER_SIZE, y + CORNER_SIZE);
    corner.lineTo(x + width, y + CORNER_SIZE);
    corner.closePath();
    drawingContext.draw(mainShape, FILL_COLOR);
    drawingContext.draw(corner, FILL_COLOR);*/
    drawingContext.drawUsercase(x, y, width, height);
    label.drawText(drawingContext, height);
  }

  /**
   * {@inheritDoc}
   */
  public UmlModelElement getModelElement() { return null; }

  /**
   * {@inheritDoc}
   */
  public boolean acceptsConnection(RelationType associationType,
    RelationEndType as, UmlNode with) {
    return true;
  }
}
