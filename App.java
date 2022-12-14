import java.io.*;
import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyListener;
import java.awt.image.AreaAveragingScaleFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.Component.*;
import java.util.*;
import java.util.Vector.*;
import java.io.IOException;

public class App extends JPanel {
    private Dictionary dic;
    private Dictionary historySearch;
    private String fileName = "slang.txt";
    private String constantData = "defaultDic.txt";
    private String historyData = "historySearch.txt";
    private HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
    private HashMap<String, ArrayList<String>> history = new HashMap<String, ArrayList<String>>();
    ArrayList<String> keySet;
    private DefaultTableModel model;
    private String questionVal;
    private String answerVal;

    public App() throws IOException {
        dic = new Dictionary();
        historySearch = new Dictionary();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(constantData));
            dic = (Dictionary) ois.readObject();
            dictionary = dic.getDictionary();
            ois.close();
        } catch (Exception e) {
            dictionary = loadDictionaryFromTextFile(fileName);
            try {
                dic.setDictionary(dictionary);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(historyData));
                oos.writeObject(dic);
                oos.close();
            } catch (Exception exp) {
            }
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(historyData));
            historySearch = (Dictionary) ois.readObject();
            history = historySearch.getDictionary();
            ois.close();
        } catch (Exception e) {
        }
        keySet = new ArrayList<String>(dictionary.keySet());
        setupUI();
    }

    public HashMap<String, ArrayList<String>> loadDictionaryFromTextFile(String fileName) {
        HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
        BufferedReader fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                if (line.contains("`")) {
                    String[] tokens = line.split("`");
                    String slang = tokens[0];
                    ArrayList<String> definition = new ArrayList<String>(Arrays.asList(tokens[1].split("\\| ")));
                    if (dictionary.containsKey(slang) == true) {
                        ArrayList<String> oldDefinition = dictionary.get(slang);
                        definition.addAll(oldDefinition);
                    }
                    dictionary.put(slang, definition);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: loadDictionaryFromTextFile!!!");
            e.printStackTrace();
        }
        System.out.println("Load Dictionary Data Successful !!");
        return dictionary;
    }

    public HashMap<String, ArrayList<String>> loadHistory(String fileName) {
        HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
        BufferedReader fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            while ((line = fileReader.readLine()) != null) {
                if (line.contains("`")) {
                    String[] tokens = line.split("`");
                    String slang = tokens[0];
                    ArrayList<String> definition = new ArrayList<String>(Arrays.asList(tokens[1].split("\\| ")));
                    if (dictionary.containsKey(slang) == true) {
                        ArrayList<String> oldDefinition = dictionary.get(slang);
                        definition.addAll(oldDefinition);
                    }
                    dictionary.put(slang, definition);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: loadHistory !!!");
            e.printStackTrace();
        }
        System.out.println("Load Dictionary Data Successful !!");
        return dictionary;
    }

    public void writeHistoryToTextFile(HashMap<String, ArrayList<String>> history, String fileName) {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("\n");
            for (Map.Entry<String, ArrayList<String>> entry : history.entrySet()) {
                String data = entry.getKey() + "`" + entry.getValue();
                System.out.println(data);
                bw.write(data);
                bw.write("\n");
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error !!!");
            e.printStackTrace();
        }
        System.out.println("Write HistorySearch Data Successful !!");
    }

    public void loadDataIntoTable(HashMap<String, ArrayList<String>> dictionary) {
        model = new DefaultTableModel();
        Vector headerColumn = new Vector();
        headerColumn.add("ID");
        headerColumn.add("Slang");
        headerColumn.add("Meaning");
        model.setColumnIdentifiers(headerColumn);
        int i = 0;
        for (Map.Entry<String, ArrayList<String>> entry : dictionary.entrySet()) {
            ArrayList<String> values = entry.getValue();
            for (String e : values) {
                i++;
                Vector row = new Vector();
                row.add(i);
                row.add(entry.getKey());
                row.add(e);
                model.addRow(row);
            }
        }
        slangTable.setModel(model);
        slangTable.getColumnModel().getColumn(0).setMaxWidth(50);
        slangTable.getColumnModel().getColumn(1).setMaxWidth(100);
        slangTable.getColumnModel().getColumn(1).setPreferredWidth(100);
    }

    public String appTitle = "SLANG WORDS DICTIONARY";
    public JPanel featurePanel;
    public JPanel appTitlePanel;
    public JLabel titleLabel;
    public JPanel searchPanel;
    public JLabel searchLabel;
    public JTextField searchField;
    public JComboBox searchType;
    public JButton searchButton;
    public JButton historyButton;
    public JButton displayDictionary;
    public JPanel tablePanel;
    public JScrollPane tableScrollPanel;
    public JTable slangTable;
    public JPanel resetPanel;
    public JButton resetButton;
    public JPanel leftPanel;
    public JPanel randomPanel;
    public JButton randomButton;
    public JLabel randomLabel;
    public JPanel detailPanel;
    public JPanel inputDetailPanel;
    public JLabel slangLabel;
    public JTextField slangField;
    public JLabel definitionLabel;
    public JTextField meaningField;
    public JPanel addEditPanel;
    public JButton addButton;
    public JButton editButton;
    public JButton deleteButton;

    public JPanel quizPanel;
    public JPanel quizTitlePanel;
    public JLabel quizTitle;
    public JButton startQuizButton;
    public JComboBox quizMode;
    public JPanel introPanel;
    public JPanel questionPanel;
    public JLabel questionLabel;
    public JPanel answerPanel;
    public JButton aButton;
    public JButton bButton;
    public JButton cButton;
    public JButton dButton;
    public JPanel actionQuizPanel;
    public JButton stopButton;
    public JButton nextButton;

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        appTitlePanel = new JPanel();
        titleLabel = new JLabel();
        searchPanel = new JPanel();
        searchLabel = new JLabel();
        searchField = new JTextField(20);
        searchType = new JComboBox<>();
        searchButton = new JButton();
        historyButton = new JButton();
        displayDictionary = new JButton();

        tablePanel = new JPanel();
        tableScrollPanel = new JScrollPane();
        slangTable = new JTable();
        resetPanel = new JPanel();
        resetButton = new JButton();

        featurePanel = new JPanel();
        randomPanel = new JPanel();
        randomButton = new JButton();
        randomLabel = new JLabel();
        detailPanel = new JPanel();
        inputDetailPanel = new JPanel();
        slangLabel = new JLabel();
        slangField = new JTextField(20);
        definitionLabel = new JLabel();
        meaningField = new JTextField(20);
        addEditPanel = new JPanel();
        addButton = new JButton();
        editButton = new JButton();
        deleteButton = new JButton();

        quizPanel = new JPanel();
        quizTitlePanel = new JPanel();
        quizTitle = new JLabel();
        introPanel = new JPanel();
        startQuizButton = new JButton();
        quizMode = new JComboBox<>();
        questionPanel = new JPanel();
        questionLabel = new JLabel();

        answerPanel = new JPanel();
        aButton = new JButton();
        bButton = new JButton();
        cButton = new JButton();
        dButton = new JButton();
        actionQuizPanel = new JPanel();
        stopButton = new JButton();
        nextButton = new JButton();

        titleLabel.setText(appTitle);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        appTitlePanel.add(titleLabel, CENTER_ALIGNMENT);
        add(appTitlePanel);
        // Start: Search panel
        searchLabel.setText("Keyword: ");

        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        searchType.setModel(new DefaultComboBoxModel<>(new String[] { "Slang", "Meaning" }));
        searchType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchComboBoxActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });

        historyButton.setText("History");
        historyButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                historyButtonMouseClicked(evt);
            }
        });
        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                historyButtonActionPerformed(evt);
            }
        });

        displayDictionary.setText("Display");
        displayDictionary.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                displayDictionaryMouseClicked(evt);
            }
        });
        displayDictionary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                displayDictionaryActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    resetButtonActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchType);
        searchPanel.add(searchButton);
        searchPanel.add(historyButton);
        searchPanel.add(displayDictionary);
        searchPanel.add(resetButton);
        add(searchPanel, LEFT_ALIGNMENT);
        // End: Search Panel

        // Start: Dictionary Panel
        slangTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Index", "Slang", "Meaning" }));
        slangTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                slangTableMouseClicked(evt);
            }
        });
        tableScrollPanel.setViewportView(slangTable);
        if (slangTable.getColumnModel().getColumnCount() > 0) {
            slangTable.getColumnModel().getColumn(0).setResizable(false);
        }

        // End: Dictionary Panel
        tablePanel.add(tableScrollPanel);
        add(tablePanel);

        // Start: Feature Panel
        randomButton.setText("Random Slang");
        randomButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                randomButtonMouseClicked(evt);
            }
        });
        randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                randomButtonActionPerformed(evt);
            }
        });

        slangLabel.setText("Slang: ");

        definitionLabel.setText("Definition:");

        addButton.setText("Add");
        addButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        addEditPanel.add(addButton);

        editButton.setText("Edit");
        editButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        addEditPanel.add(editButton);

        deleteButton.setText("Delete");
        deleteButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        addEditPanel.add(deleteButton);
        // Layout Feature Panel
        featurePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        featurePanel.add(slangLabel, gbc);
        gbc.gridx += 2;
        featurePanel.add(randomButton, gbc);
        gbc.gridx++;
        featurePanel.add(addButton, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        featurePanel.add(definitionLabel, gbc);
        gbc.gridx += 2;
        featurePanel.add(editButton, gbc);
        gbc.gridx++;
        featurePanel.add(deleteButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.5;
        featurePanel.add(slangField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        featurePanel.add(meaningField, gbc);
        featurePanel.setBorder(new CompoundBorder(new TitledBorder("Function"), new EmptyBorder(5, 5, 5, 5)));
        add(featurePanel);
        // End: Feature Panel

        // Start: Quiz Panel
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));

        quizTitle.setText("LET START THE QUIZ");
        quizTitle.setFont(new Font("Serif", Font.BOLD, 25));
        quizTitlePanel.add(quizTitle, CENTER_ALIGNMENT);

        startQuizButton.setText("Start Quiz");
        startQuizButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                startQuizButtonMouseClicked(evt);
            }
        });
        startQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startQuizButtonActionPerformed(evt);
            }
        });

        quizMode.setModel(new DefaultComboBoxModel<>(new String[] { "Slang", "Meaning" }));
        quizMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                quizModeActionPerformed(evt);
            }
        });

        questionLabel.setText("");
        questionPanel.add(questionLabel, CENTER_ALIGNMENT);

        introPanel.add(startQuizButton, LEFT_ALIGNMENT);
        introPanel.add(quizMode, RIGHT_ALIGNMENT);
        quizPanel.add(quizTitlePanel);
        quizPanel.add(introPanel);

        quizPanel.add(questionPanel);

        answerPanel.setLayout(new GridLayout(2, 2, 10, 10));

        aButton.setText("A");
        aButton.setEnabled(false);
        aButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                aButtonMouseClicked(evt);
            }
        });
        answerPanel.add(aButton);

        bButton.setText("B");
        bButton.setEnabled(false);
        bButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                bButtonMouseClicked(evt);
            }
        });
        answerPanel.add(bButton);

        cButton.setText("C");
        cButton.setEnabled(false);
        cButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                cButtonMouseClicked(evt);
            }
        });
        answerPanel.add(cButton);

        dButton.setText("D");
        dButton.setEnabled(false);
        dButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dButtonMouseClicked(evt);
            }
        });
        answerPanel.add(dButton);

        quizPanel.add(answerPanel);

        stopButton.setText("Stop Game");
        stopButton.setEnabled(false);
        stopButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                stopButtonMouseClicked(evt);
            }
        });
        actionQuizPanel.add(stopButton, LEFT_ALIGNMENT);

        quizPanel.add(actionQuizPanel);
        quizPanel.setBorder(new CompoundBorder(new TitledBorder("Quiz"), new EmptyBorder(5, 5, 5, 5)));
        add(quizPanel);
    }

    private void searchFieldActionPerformed(ActionEvent evt) {
    }

    private void searchComboBoxActionPerformed(ActionEvent evt) {
    }

    private void historyButtonActionPerformed(ActionEvent evt) {
    }

    private void historyButtonMouseClicked(MouseEvent evt) {
        history = loadHistory(historyData);
        loadDataIntoTable(history);
    }

    private void displayDictionaryActionPerformed(ActionEvent evt) {
    }

    private void displayDictionaryMouseClicked(MouseEvent evt) {
        loadDataIntoTable(dictionary);
    }

    private void resetButtonActionPerformed(ActionEvent evt) throws IOException {
        dictionary.clear();
        dictionary = new HashMap<String, ArrayList<String>>();
        dictionary = loadDictionaryFromTextFile(fileName);
        try {
            dic.setDictionary(dictionary);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(constantData));
            oos.writeObject(dic);
            oos.close();
        } catch (Exception exp) {
        }
        // }
        loadDataIntoTable(dictionary);
    }

    private void randomButtonActionPerformed(ActionEvent evt) {
    }

    private void addButtonActionPerformed(ActionEvent evt) {
    }

    private void editButtonActionPerformed(ActionEvent evt) {
    }

    private void deleteButtonActionPerformed(ActionEvent evt) {
    }

    private void startQuizButtonActionPerformed(ActionEvent evt) {
    }

    private void slangTableMouseClicked(MouseEvent evt) {
        int selectedIndex = slangTable.getSelectedRow();
        slangField.setText(model.getValueAt(selectedIndex, 1).toString());
        meaningField.setText(model.getValueAt(selectedIndex, 2).toString());

    }

    private void searchButtonMouseClicked(MouseEvent evt) {

        if (searchField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid !! Nothing To Search");
        } else {
            try {
                String keyword = searchField.getText().toString();
                String typeToSearch = (String) searchType.getSelectedItem();
                HashMap<String, ArrayList<String>> results = new HashMap<String, ArrayList<String>>();
                if (typeToSearch.equals("Meaning")) {
                    results = dic.searchByDefinition(keyword);
                    loadDataIntoTable(results);
                    writeHistoryToTextFile(results, historyData);
                } else if (typeToSearch.equals("Slang")) {
                    results = dic.searchByKeySlang(keyword);
                    loadDataIntoTable(results);
                    writeHistoryToTextFile(results, historyData);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid !! Enter Again");
            }
        }
    }

    private void searchFieldKeyReleased(KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (searchField.getText().equals("")) {
                loadDataIntoTable(dictionary);
            }
        }
    }

    private void addButtonMouseClicked(MouseEvent evt) {

        if (slangField.getText().equals("") || meaningField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid !! Please Enter All Information");
        } else {
            try {
                String slang = slangField.getText().toString();
                String definition = meaningField.getText().toString();

                if (dictionary.containsKey(slang) == true) {
                    Object[] options = { "Duplicate", "Overwrite", "Cancel" };
                    int result = JOptionPane.showOptionDialog(this,
                            "This slang is existed. Do you want make duplicate?",
                            "Choose Duplicate or Overwrite",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (result == JOptionPane.YES_OPTION) {
                        ArrayList<String> oldDefinition = dictionary.get(slang);
                        oldDefinition.add(definition);
                        dictionary.put(slang, oldDefinition);
                    } else if (result == JOptionPane.NO_OPTION) {
                        ArrayList<String> definitions = new ArrayList<String>();
                        definitions.add(definition);
                        dictionary.put(slang, definitions);
                    } else if (result == JOptionPane.CANCEL_OPTION) {
                        return;
                    }

                } else {
                    ArrayList<String> definitions = new ArrayList<String>();
                    definitions.add(definition);
                    dictionary.put(slang, definitions);
                }
                JOptionPane.showMessageDialog(this, "Add Successfully");
                slangField.setText("");
                meaningField.setText("");
                loadDataIntoTable(dictionary);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error !! Try Again");
            }
        }

    }

    private void editButtonMouseClicked(MouseEvent evt) {

        int selectedIndex = slangTable.getSelectedRow();
        if (dictionary.size() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Nothing To Update");
        } else if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this,
                    "Let's pick some slang to update");
        } else if (slangField.getText().equals("") || meaningField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid !! Please Enter All Information");
        } else {
            try {
                String slang = slangField.getText().toString();
                String newDefinition = meaningField.getText().toString();
                String oldSlang = model.getValueAt(selectedIndex, 1).toString();
                String oldDefinition = model.getValueAt(selectedIndex, 2).toString();
                int ret = JOptionPane.showConfirmDialog(this, "Do You Want To Update?", "Confirm",
                        JOptionPane.YES_NO_OPTION);
                if (ret != JOptionPane.YES_OPTION) {
                    return;
                }
                if (dictionary.containsKey(slang) == true) {
                    ArrayList<String> oldDefis = dictionary.get(slang);
                    boolean founded = false;
                    for (int i = 0; i < oldDefis.size(); i++) {
                        if (oldDefis.get(i).equals(oldDefinition)) {
                            founded = true;
                            oldDefis.set(i, newDefinition);
                            break;
                        }
                    }
                    if (founded) {
                        dictionary.put(slang, oldDefis);
                        JOptionPane.showMessageDialog(this, "Edit Successfully");
                        slangField.setText("");
                        meaningField.setText("");
                        loadDataIntoTable(dictionary);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Old value doesn't match anything of new key to update");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This Slang Is Not Exist");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error !! Try again");
            }
        }
    }

    private void deleteButtonMouseClicked(MouseEvent evt) {

        int selectedIndex = slangTable.getSelectedRow();
        if (dictionary.size() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Nothing here to update");
        } else if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this,
                    "Let's pick some slang to delete");
        } else if (slangField.getText().equals("") || meaningField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid !! Please Enter All Information");
        } else {
            try {
                String slang = slangField.getText().toString();
                String newDefinition = meaningField.getText().toString();
                String oldSlang = model.getValueAt(selectedIndex, 1).toString();
                String oldDefinition = model.getValueAt(selectedIndex, 2).toString();
                int ret = JOptionPane.showConfirmDialog(this, "Do You Want To Delete?", "Confirm",
                        JOptionPane.YES_NO_OPTION);
                if (ret != JOptionPane.YES_OPTION) {
                    return;
                }
                if (dictionary.containsKey(slang) == true) {
                    ArrayList<String> oldDefis = dictionary.get(slang);
                    int foundedAt = -1;

                    for (int i = 0; i < oldDefis.size(); i++) {
                        if (oldDefis.get(i).equals(newDefinition)) {
                            foundedAt = i;
                            break;
                        }
                    }
                    if (foundedAt >= 0) {
                        oldDefis.remove(foundedAt);
                        if (oldDefis.size() == 0) {
                            dictionary.remove(slang);
                        } else {
                            dictionary.put(slang, oldDefis);
                        }
                        JOptionPane.showMessageDialog(this, "Delete Successfully");
                        slangField.setText("");
                        meaningField.setText("");
                        loadDataIntoTable(dictionary);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "This value doesn't match anything to delete");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This Slang Is Not Exist");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error !! Try again");
            }

        }
    }

    public void randomSlang() {
        Random random = new Random();
        int randomIndex = random.nextInt(keySet.size());
        String slangRandom = keySet.get(randomIndex);
        String meaning = dictionary.get(slangRandom).get(0);
        slangField.setText(slangRandom);
        meaningField.setText(meaning);
    }

    private void randomButtonMouseClicked(MouseEvent evt) {
        randomSlang();
    }

    private void showQuestion() {
        Random random = new Random();
        int randomIndex = random.nextInt(keySet.size());
        String keySlang = keySet.get(randomIndex);
        String defini = dictionary.get(keySlang).get(0);
        String modePlay = (String) quizMode.getSelectedItem();
        if (modePlay.equals("Slang")) {
            randomIndex = random.nextInt(keySet.size());
            aButton.setText(dictionary.get(keySet.get(randomIndex)).get(0));
            randomIndex = random.nextInt(keySet.size());
            bButton.setText(dictionary.get(keySet.get(randomIndex)).get(0));
            randomIndex = random.nextInt(keySet.size());
            cButton.setText(dictionary.get(keySet.get(randomIndex)).get(0));
            randomIndex = random.nextInt(keySet.size());
            dButton.setText(dictionary.get(keySet.get(randomIndex)).get(0));
            randomIndex = random.nextInt(4);
            questionVal = keySlang;
            answerVal = defini;
            if (randomIndex == 0)
                aButton.setText(defini);
            else if (randomIndex == 1)
                bButton.setText(defini);
            else if (randomIndex == 2)
                cButton.setText(defini);
            else
                dButton.setText(defini);
            questionLabel.setText("What is meaning of " + questionVal + "?");
        } else {
            randomIndex = random.nextInt(keySet.size());
            aButton.setText(keySet.get(randomIndex));
            randomIndex = random.nextInt(keySet.size());
            bButton.setText(keySet.get(randomIndex));
            randomIndex = random.nextInt(keySet.size());
            cButton.setText(keySet.get(randomIndex));
            randomIndex = random.nextInt(keySet.size());
            dButton.setText(keySet.get(randomIndex));
            randomIndex = random.nextInt(4);
            questionVal = defini;
            answerVal = keySlang;
            if (randomIndex == 0)
                aButton.setText(keySlang);
            else if (randomIndex == 1)
                bButton.setText(keySlang);
            else if (randomIndex == 2)
                cButton.setText(keySlang);
            else
                dButton.setText(keySlang);
            questionLabel.setText("What is slang of: " + questionVal + "?");
        }
    }

    private void startQuizButtonMouseClicked(MouseEvent evt) {

        showQuestion();
        aButton.setEnabled(true);
        bButton.setEnabled(true);
        cButton.setEnabled(true);
        dButton.setEnabled(true);
        stopButton.setEnabled(true);
        nextButton.setEnabled(true);
    }

    private void aButtonMouseClicked(MouseEvent evt) {

        if (aButton.isEnabled()) {
            String value = aButton.getText();
            if (value.equals(answerVal)) {
                JOptionPane.showMessageDialog(this, "Correct");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect");
            }
        }
    }

    private void bButtonMouseClicked(MouseEvent evt) {

        if (bButton.isEnabled()) {
            String value = bButton.getText();
            if (value.equals(answerVal)) {
                JOptionPane.showMessageDialog(this, "Correct");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect");
            }
        }
    }

    private void cButtonMouseClicked(MouseEvent evt) {

        if (cButton.isEnabled()) {
            String value = cButton.getText();
            if (value.equals(answerVal)) {
                JOptionPane.showMessageDialog(this, "Correct");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect");
            }
        }
    }// GEN-LAST:event_cButtonMouseClicked

    private void dButtonMouseClicked(MouseEvent evt) {

        if (dButton.isEnabled()) {
            String value = dButton.getText();
            if (value.equals(answerVal)) {
                JOptionPane.showMessageDialog(this, "Correct");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect");
            }
        }
    }// GEN-LAST:event_dButtonMouseClicked

    private void quizModeActionPerformed(ActionEvent evt) {
    }

    private void stopButtonMouseClicked(MouseEvent evt) {
        if (stopButton.isEnabled()) {
            aButton.setEnabled(false);
            bButton.setEnabled(false);
            cButton.setEnabled(false);
            dButton.setEnabled(false);
            stopButton.setEnabled(false);
            nextButton.setEnabled(false);

            aButton.setText("A");
            bButton.setText("B");
            cButton.setText("C");
            dButton.setText("D");
            questionLabel.setText("Question: ");
        }
    }

    public static void createAndShowGUI() throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Slang Words Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        App newContentPane = new App();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        App.createAndShowGUI();
    }
}
