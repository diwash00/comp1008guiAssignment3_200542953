    import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javafx.scene.control.ColorPicker;

    public class mainScreenController {


    @FXML
    private Button addStudent;

    @FXML
    private Rectangle box1;

    @FXML
    private Rectangle box2;

    @FXML
    private Rectangle box3;

    @FXML
    private Rectangle box4;

    @FXML
    private Rectangle box5;

    @FXML
    private Rectangle box6;

    @FXML
    private Rectangle box7;

    @FXML
    private Rectangle box8;

    @FXML
    private Rectangle box9;

    @FXML
    private Label error;

    @FXML
    private Label name1;

    @FXML
    private Label name2;

    @FXML
    private Label name3;

    @FXML
    private Label name4;

    @FXML
    private Label name5;

    @FXML
    private Label name6;

    @FXML
    private Label name7;

    @FXML
    private Label name8;

    @FXML
    private Label name9;

    @FXML
    private TextField sName;

    @FXML
    private ColorPicker pColor;

    @FXML
    public void initialize() {
        pColor.setValue(Color.WHITE);
        pColor.setValue(null);
        error.setText("");
    }

    @FXML
    public void onAddStudentClick(ActionEvent event) {
        String studentName = sName.getText();


        ArrayList<Label> studentNameLabels = new ArrayList<Label>();
        ArrayList<Rectangle> studentDeskBoxes = new ArrayList<Rectangle>();

        Set<Color> assignedDesks = new HashSet<>();
        Set<Label> assignedNames = new HashSet<>();
        Set<Color> usedColors = new HashSet<>();

        studentNameLabels.add(name1);
        studentNameLabels.add(name2);
        studentNameLabels.add(name3);
        studentNameLabels.add(name4);
        studentNameLabels.add(name5);
        studentNameLabels.add(name6);
        studentNameLabels.add(name7);
        studentNameLabels.add(name8);
        studentNameLabels.add(name9);

        studentDeskBoxes.add(box1);
        studentDeskBoxes.add(box2);
        studentDeskBoxes.add(box3);
        studentDeskBoxes.add(box4);
        studentDeskBoxes.add(box5);
        studentDeskBoxes.add(box6);
        studentDeskBoxes.add(box7);
        studentDeskBoxes.add(box8);
        studentDeskBoxes.add(box9);


        Color selectedColor = pColor.getValue();
        if(selectedColor == null){
            error.setText("ERROR:You did not choose any color");
            return;
        }

        if(sName.getText().trim().isEmpty()){
            error.setText("ERROR: Student name field cannot be blank");
            return;
        } else if(sName.getText().trim().length() < 3) {
            error.setText("ERROR: Student name must be at least three characters");
            return;
        }

        for(int i = 0; i < studentNameLabels.size(); i++){
            error.setText("");
            if (studentNameLabels.get(i).getText().equals(studentName)) {
                error.setText("ERROR: Student name ' "+  studentName  + " ' already exists.");
                return;
            }
        }

        for (int i = 0; i < studentNameLabels.size(); i++) {
            if(studentNameLabels.get(i).getText().isEmpty()){
                assignedNames.add(studentNameLabels.get(i));
                usedColors.add((Color)studentDeskBoxes.get(i).getFill());
            }else if(studentNameLabels.get(i).getText().equals(studentName)){
                return;
            }else{
                assignedDesks.add((Color)studentDeskBoxes.get(i).getFill());
                usedColors.add((Color)studentDeskBoxes.get(i).getFill());
            }
        }

        if(!assignedNames.isEmpty()){
            if(usedColors.contains(selectedColor)) {
                error.setText("ERROR: White can not be Chosen");
                return;
            }
            Random random = new Random();
            Label randomLabel = assignedNames.stream().skip(random.nextInt(assignedNames.size())).findFirst().orElse(null);
            if(randomLabel != null){
                randomLabel.setText(studentName);
                assignedNames.add(randomLabel);
            }
            int assignedLabelIndex = studentNameLabels.indexOf(randomLabel);
            Rectangle emptyDesk = studentDeskBoxes.get(assignedLabelIndex);
            emptyDesk.setFill(selectedColor);
            assignedDesks.add(selectedColor);
        }

        int numStudents = 0;
        for (int i = 0; i < studentNameLabels.size(); i++) {
            if (!studentNameLabels.get(i).getText().isEmpty()) {
                numStudents++;
            }
        }

        if (numStudents >= 9)
        {

            error.setText("The class is full!");
            error.setTextFill(Color.GREEN);
            pColor.setValue(Color.WHITE);
            return;
        }



    }
}

