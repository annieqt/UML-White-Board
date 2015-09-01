package whiteboard.util.draw;

import java.awt.font.TextLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import whiteboard.util.draw.DrawingContext.FontType;
import whiteboard.util.draw.MultilineLayouter.MultilineLayout;

/**
 * This class implements a Label with multiple lines. It is important for
 * performance to implement a dedicated multiline label because layout is
 * a rather slow operation.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class CustomLabel extends SimpleLabel {

  private static final long serialVersionUID = 3993300155345177335L;
  private transient MultilineLayout layout;

  /**
   * Writes the instance variables to the stream.
   * @param stream an ObjectOutputStream
   * @throws IOException if I/O error occured
   */
  @SuppressWarnings("PMD.UnusedFormalParameter")
  private void writeObject(ObjectOutputStream stream) throws IOException {
    // layout should not be written
  }

  /**
   * Reset the transient values for serialization.
   * @param stream an ObjectInputStream
   * @throws IOException if I/O error occured
   * @throws ClassNotFoundException if class was not found
   */
  @SuppressWarnings("PMD.UnusedFormalParameter")
  private void readObject(ObjectInputStream stream)
    throws IOException, ClassNotFoundException {
    layout = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void draw(DrawingContext drawingContext) {
    double x = getAbsoluteX1(), y = getAbsoluteY1();
    double layoutWidth = getSize().getWidth();
    double layoutHeight = getSize().getHeight();
    double textY = y+layoutHeight;
    if (layout == null) recalculateSize(drawingContext);
    for (TextLayout line : layout.getLines()) {
      // Set the left position of the text depending on the text layout
      // direction
      double textX = line.isLeftToRight() ? x : layoutWidth - line.getAdvance();
      textY += line.getAscent();
      line.draw(drawingContext.getGraphics2D(), (float) textX, (float) textY);
      // Move text position another step downward
      textY += line.getDescent() + line.getLeading();
    }
  }
  
  public void drawText(DrawingContext drawingContext, double y1) {
	    double x = getAbsoluteX1(), y = getAbsoluteY1();
	    double layoutWidth = getSize().getWidth();
	    double layoutHeight = getSize().getHeight();
	    double textY = y+y1-0.6*layoutHeight;
	    if (layout == null) recalculateSize(drawingContext);
	    for (TextLayout line : layout.getLines()) {
	      // Set the left position of the text depending on the text layout
	      // direction
	      double textX = line.isLeftToRight() ? x : layoutWidth - line.getAdvance();
	      textY += line.getAscent();
	      line.draw(drawingContext.getGraphics2D(), (float) textX, (float) textY);
	      // Move text position another step downward
	      textY += line.getDescent() + line.getLeading();
	    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void recalculateSize(DrawingContext drawingContext) {
    layout = MultilineLayouter.getInstance().calculateLayout(
      drawingContext.getGraphics2D().getFontRenderContext(),
      drawingContext.getFont(FontType.DEFAULT), getText(),
      getSize().getWidth());
    setSize(layout.getSize().getWidth(), layout.getSize().getHeight());
    setValid(true);
  }
}

