package application;

public class Avtaletest {


//Adding a Label
final Label label = new Label();
GridPane.setConstraints(label, 0, 3);
GridPane.setColumnSpan(label, 2);
grid.getChildren().add(label);

//Setting an action for the Submit button
submit.setOnAction(new EventHandler<ActionEvent>() {

@Override
  public void handle(ActionEvent e) {
      if ((comment.getText() != null && !comment.getText().isEmpty())) {
          label.setText(name.getText() + " " + lastName.getText() + ", "
              + "thank you for your comment!");
      } else {
          label.setText("You have not left a comment.");
      }
   }
});

//Setting an action for the Clear button
clear.setOnAction(new EventHandler<ActionEvent>() {

@Override
  public void handle(ActionEvent e) {
      name.clear();
      lastName.clear();
      comment.clear();
      label.setText(null);
  }
});
}