import java.util.ArrayList;
import java.io.*;

public class List {
    private ArrayList<Task> list;

    public List() {
        this.list = new ArrayList<>();
        loadFromFile();
    }

    public void addToDo(String text) {
        Task newToDo = new ToDo(text);
        list.add(newToDo);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newToDo);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        writeToFile();
    }

    public void addDeadline(String text, String by) throws DukeException {
        Task newDl = new Deadline(text, by);
        list.add(newDl);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newDl);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        writeToFile();
    }

    public void addEvent(String text, String at) {
        Task newEvent = new Event(text, at);
        list.add(newEvent);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newEvent);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        writeToFile();
    }

    public void setIndexDone(int index) {// starts from 1
        if(index > list.size() || index < 1){//check for invalid index number
            System.out.println("There is no task " + index);
            return;
        }
        list.get(index - 1).setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(list.get(index - 1).toString());
        writeToFile();
    }

    public void deleteTask(int index) {//starts from 1
        if(index > list.size() || index < 1){
            System.out.println("There is no task " + index);
            return;
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(list.get(index - 1).toString());
        list.remove(index - 1);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        writeToFile();
    }

    public void show() {
        int length = list.size();
        if (length == 0) {
            System.out.println("YAY! You have no more tasks left :)");
        }
        for(int i = 1; i <= length; i++) {
            System.out.println(i + "." + list.get(i - 1).toString());
        }
    }
    private void writeToFile() {
        try {
            FileOutputStream writeData = new FileOutputStream("list.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(list);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        try{
            FileInputStream readData = new FileInputStream("list.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            ArrayList list2 = (ArrayList<Task>) readStream.readObject();
            this.list = list2;
            readStream.close();
        }catch (FileNotFoundException e) {
            return;
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
