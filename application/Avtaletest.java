Label labelFile = new Label();

Button btn = new Button();

btn.setText("Browse File");

btn.setOnAction(new EventHandler<ActionEvent>() {

              @Override


              public void handle(ActionEvent event) {


                  FileChooser fileChooser = new FileChooser();


                  
                  fileChooser.setTitle("Select File");


                  //Set extension filter


                  FileChooser.ExtensionFilter extFilter =

new FileChooser.ExtensionFilter("*", "*");

                  fileChooser.getExtensionFilters().add(extFilter);

                 


                  //Show open file dialog

                  File file = fileChooser.showOpenDialog(null);

                 if(file!=null)

                  labelFile.setText(file.getPath());

              }

});
