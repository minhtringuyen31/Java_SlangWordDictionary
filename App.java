import java.io.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
import java.io.IOException;;

public class App extends JPanel {
    private HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
    private HashMap<String, ArrayList<String>> history = new HashMap<String, ArrayList<String>>();
    private String fileName = "slang.txt";
    private String constantData = "standardData";
    private String historyData = "historySearch.txt";
    private Dictionary dic;
    private Dictionary histoSerial;
    ArrayList<String> keySet;
    private DefaultTableModel model;

    public App() throws IOException {
        // dictionary = loadDictionaryFromTextFile(fileName);
        // dic.setDictionary(dictionary);
        dic = new Dictionary();
        histoSerial = new Dictionary();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(constantData));
            dic = (Dictionary) ois.readObject();
            dictionary = dic.getDictionary();
            ois.close();
            // System.out.println("read serialize");
        } catch (Exception e) {
            dictionary = loadDictionaryFromTextFile(fileName);
            try {
                dic.setDictionary(dictionary);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(historyData));
                oos.writeObject(dic);

                oos.close();
            } catch (Exception exp) {
                // System.out.println("can't serialize");
            }
        }
        // history
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(historyData));
            histoSerial = (Dictionary) ois.readObject();
            history = histoSerial.getDictionary();
            ois.close();
            // System.out.println("read history serialize");
        } catch (Exception e) {
            // System.out.println("can't history serialize");
        }

        keySet = new ArrayList<String>(dictionary.keySet());
        setupUI();
        loadDataIntoTable(dictionary);

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
            System.out.println("Error in readFromTextFile !!!");
            e.printStackTrace();
        }
        System.out.println("Load Dictionary Data Successful !!");
        return dictionary;
    }

    public void loadDataIntoTable(HashMap<String, ArrayList<String>> dictionary) {
        // System.out.println("into load data into db");
        model = new DefaultTableModel();
        Vector headerColumn = new Vector();
        headerColumn.add("ID");
        headerColumn.add("Slang");
        headerColumn.add("Definition");

        model.setColumnIdentifiers(headerColumn);
        // set data
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
        slangTable.getColumnModel().getColumn(0).setMaxWidth(45);
        slangTable.getColumnModel().getColumn(1).setMaxWidth(120);
        slangTable.getColumnModel().getColumn(1).setPreferredWidth(100);
    }

    public String appTitle = "SLANG WORDS DICTIONARY";
    public JPanel featurePanel;
    public JPanel headerPane;
    public JLabel titleLabel;
    public JPanel searchPanel;
    public JLabel searchLabel;
    public JTextField searchField;
    public JComboBox searchType;
    public JButton searchButton;
    public JButton historyButton;
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
    public JTextField definitionField;
    public JPanel addEditPanel;
    public JButton addButton;
    public JButton editButton;
    public JButton deleteButton;

    public void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel = new JLabel();
        searchPanel = new JPanel();
        searchLabel = new JLabel();
        searchField = new JTextField(20);
        searchType = new JComboBox<>();
        searchButton = new JButton();
        historyButton = new JButton();

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
        definitionField = new JTextField(20);
        addEditPanel = new JPanel();
        addButton = new JButton();
        editButton = new JButton();
        deleteButton = new JButton();
        // playPane = new JPanel();
        // questionPane = new JPanel();
        // questionLabel = new JLabel();
        // jLabel1 = new JLabel();
        // modeComboBox = new JComboBox<>();
        // jPanel2 = new JPanel();
        // playButton = new JButton();
        // answerPane = new JPanel();
        // aButton = new JButton();
        // bButton = new JButton();
        // cButton = new JButton();
        // dButton = new JButton();
        // jPanel1 = new JPanel();
        // stopButton = new JButton();
        // nextButton = new JButton();

        titleLabel.setText(appTitle);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        add(titleLabel, CENTER_ALIGNMENT);
        // begin: search panel
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

        searchType.setModel(new DefaultComboBoxModel<>(new String[] { "Slang", "definitiontion" }));
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
        // End: search panel
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(historyButton);
        add(searchPanel, LEFT_ALIGNMENT);

        // Start: Dictionary panel
        slangTable.setModel(new DefaultTableModel(
                new Object[][] {
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null }
                },
                new String[] {
                        "Index", "Slang", "definitiontion"
                }));
        slangTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                slangTableMouseClicked(evt);
            }
        });
        tableScrollPanel.setViewportView(slangTable);
        if (slangTable.getColumnModel().getColumnCount() > 0) {
            slangTable.getColumnModel().getColumn(0).setResizable(false);
        }

        resetButton.setText("Reset Default Dictionary");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    resetButtonActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        resetPanel.add(resetButton);
        // End: dictionary Panel
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

        // featurePanel.setBorder(new CompoundBorder(new TitledBorder("Feature"), new
        // EmptyBorder(5,5,5,5)));
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
        featurePanel.add(definitionField, gbc);
        featurePanel.setBorder(new CompoundBorder(new TitledBorder("Function"), new EmptyBorder(5, 5, 5, 5)));
        add(featurePanel);

        // playPane.setBorder(javax.swing.BorderFactory.createLineBorder(new
        // java.awt.Color(0, 0, 0)));

        // questionLabel.setText("Question: ");

        // jLabel1.setText("Mode:");

        // modeComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Slang",
        // "definitiontion" }));
        // modeComboBox.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent evt) {
        // modeComboBoxActionPerformed(evt);
        // }
        // });

        // playButton.setText("Let's Play Quiz Game");
        // playButton.addMouseListener(new MouseAdapter() {
        // public void mouseClicked(MouseEvent evt) {
        // playButtonMouseClicked(evt);
        // }
        // });
        // playButton.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent evt) {
        // playButtonActionPerformed(evt);
        // }
        // });
        // jPanel2.add(playButton);

        // javax.swing.GroupLayout questionPaneLayout = new GroupLayout(questionPane);
        // questionPane.setLayout(questionPaneLayout);
        // questionPaneLayout.setHorizontalGroup(
        // questionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addComponent(questionLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        // .addGroup(questionPaneLayout.createSequentialGroup()
        // .addComponent(jLabel1)
        // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        // .addComponent(modeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        // .addGap(0, 0, Short.MAX_VALUE))
        // .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        // questionPaneLayout.setVerticalGroup(
        // questionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addGroup(questionPaneLayout.createSequentialGroup()
        // .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        // .addGroup(questionPaneLayout
        // .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        // .addComponent(jLabel1)
        // .addComponent(modeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.PREFERRED_SIZE))
        // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        // .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
        // javax.swing.GroupLayout.PREFERRED_SIZE)));

        // answerPane.setLayout(new java.awt.GridLayout(2, 2, 20, 10));

        // aButton.setText("A. ");
        // aButton.setEnabled(false);
        // aButton.addMouseListener(new MouseAdapter() {
        // public void mouseClicked(MouseEvent evt) {
        // aButtonMouseClicked(evt);
        // }
        // });
        // answerPane.add(aButton);

        // bButton.setText("B. ");
        // bButton.setEnabled(false);
        // bButton.addMouseListener(new MouseAdapter() {
        // public void mouseClicked(MouseEvent evt) {
        // bButtonMouseClicked(evt);
        // }
        // });
        // answerPane.add(bButton);

        // cButton.setText("C. ");
        // cButton.setEnabled(false);
        // cButton.addMouseListener(new MouseAdapter() {
        // public void mouseClicked(MouseEvent evt) {
        // cButtonMouseClicked(evt);
        // }
        // });
        // answerPane.add(cButton);

        // dButton.setText("D. ");
        // dButton.setEnabled(false);
        // dButton.addMouseListener(new MouseAdapter() {
        // public void mouseClicked(MouseEvent evt) {
        // dButtonMouseClicked(evt);
        // }
        // });
        // answerPane.add(dButton);

        // stopButton.setText("Stop Game");
        // stopButton.setEnabled(false);
        // stopButton.addMouseListener(new MouseAdapter() {
        // public void mouseClicked(MouseEvent evt) {
        // stopButtonMouseClicked(evt);
        // }
        // });
        // jPanel1.add(stopButton);

        // nextButton.setText("Next Question");
        // nextButton.setEnabled(false);
        // nextButton.addMouseListener(new MouseAdapter() {
        // public void mouseClicked(MouseEvent evt) {
        // nextButtonMouseClicked(evt);
        // }
        // });
        // nextButton.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent evt) {
        // nextButtonActionPerformed(evt);
        // }
        // });
        // jPanel1.add(nextButton);

        // javax.swing.GroupLayout playPaneLayout = new GroupLayout(playPane);
        // playPane.setLayout(playPaneLayout);
        // playPaneLayout.setHorizontalGroup(
        // playPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addComponent(questionPane, javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        // .addComponent(answerPane, javax.swing.GroupLayout.Alignment.TRAILING,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
        // Short.MAX_VALUE)
        // .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 393,
        // Short.MAX_VALUE));
        // playPaneLayout.setVerticalGroup(
        // playPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addGroup(playPaneLayout.createSequentialGroup()
        // .addComponent(questionPane, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        // .addComponent(answerPane, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        // .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        // .addContainerGap()));

        // javax.swing.GroupLayout leftPaneLayout = new GroupLayout(leftPane);
        // leftPane.setLayout(leftPaneLayout);
        // leftPaneLayout.setHorizontalGroup(
        // leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addComponent(randomPane, javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        // .addComponent(detailPane, javax.swing.GroupLayout.Alignment.TRAILING,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
        // Short.MAX_VALUE)
        // .addGroup(leftPaneLayout.createSequentialGroup()
        // .addGap(10, 10, 10)
        // .addComponent(playPane, javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        // leftPaneLayout.setVerticalGroup(
        // leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addGroup(leftPaneLayout.createSequentialGroup()
        // .addComponent(randomPane, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        // .addComponent(detailPane, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        // .addComponent(playPane, javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        // .addContainerGap()));

        // javax.swing.GroupLayout layout = new GroupLayout(this);
        // this.setLayout(layout);
        // layout.setHorizontalGroup(
        // layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addComponent(headerPane, javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        // .addGroup(layout.createSequentialGroup()
        // .addComponent(leftPane, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        // .addComponent(tablePane, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.PREFERRED_SIZE)));
        // layout.setVerticalGroup(
        // layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addGroup(layout.createSequentialGroup()
        // .addComponent(headerPane, javax.swing.GroupLayout.PREFERRED_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        // .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
        // false)
        // .addComponent(tablePane, javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        // .addComponent(leftPane, javax.swing.GroupLayout.DEFAULT_SIZE,
        // javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
    }

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void historyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void historyButtonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        // HistoryForm histoFrame = new HistoryForm(history);
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
            // System.out.println("can't reset serialize");
        }
        // }
        loadDataIntoTable(dictionary);
    }

    private void randomButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void slangTableMouseClicked(java.awt.event.MouseEvent evt) {
        int selectedIndex = slangTable.getSelectedRow();
        slangField.setText(model.getValueAt(selectedIndex, 1).toString());
        definitionField.setText(model.getValueAt(selectedIndex, 2).toString());

    }

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

        if (searchField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter something to search");
        } else {
            try {
                String keyword = searchField.getText().toString();
                String typeToSearch = (String) searchType.getSelectedItem();
                HashMap<String, ArrayList<String>> results = new HashMap<String, ArrayList<String>>();
                if (typeToSearch.equals("Definition")) {
                    results = dic.searchByDefinition(keyword);
                    loadDataIntoTable(results);
                } else if (typeToSearch.equals("Slang")) {
                    results = dic.searchByKeySlang(keyword);
                    loadDataIntoTable(results);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please Enter full information or valid field");
            }
        }

    }

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (searchField.getText().equals("")) {
                loadDataIntoTable(dictionary);
            }
        }
    }

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        if (slangField.getText().equals("") || definitionField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter full information");
        } else {
            try {
                String slang = slangField.getText().toString();
                String definition = definitionField.getText().toString();

                if (dictionary.containsKey(slang) == true) {
                    Object[] options = { "Duplicate", "Overwrite", "Cancel" };
                    int result = JOptionPane.showOptionDialog(this,
                            "This slang is existed. Do you want make duplicate?",
                            "Choose Duplicate or Overwrite",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (result == JOptionPane.YES_OPTION) {
                        // System.out.println("duplicate");
                        ArrayList<String> oldDefinition = dictionary.get(slang);
                        oldDefinition.add(definition);
                        dictionary.put(slang, oldDefinition);
                    } else if (result == JOptionPane.NO_OPTION) {
                        // System.out.println("Overwrite");
                        ArrayList<String> definitions = new ArrayList<String>();
                        definitions.add(definition);
                        dictionary.put(slang, definitions);
                    } else if (result == JOptionPane.CANCEL_OPTION) {
                        // System.out.println("cancel");
                        return;
                    }

                } else {
                    ArrayList<String> definitions = new ArrayList<String>();
                    definitions.add(definition);
                    dictionary.put(slang, definitions);
                }
                JOptionPane.showMessageDialog(this, "Add Successfully");
                slangField.setText("");
                definitionField.setText("");
                loadDataIntoTable(dictionary);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "There are some error, please try again");
            }
        }

    }

    private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int selectedIndex = slangTable.getSelectedRow();
        if (dictionary.size() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Nothing here to update");
        } else if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this,
                    "Let's pick some slang to update");
        } else if (slangField.getText().equals("") || definitionField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter full information");
        } else {
            try {
                String slang = slangField.getText().toString();
                String newDefinition = definitionField.getText().toString();
                String oldSlang = model.getValueAt(selectedIndex, 1).toString();
                String oldDefinition = model.getValueAt(selectedIndex, 2).toString();
                int ret = JOptionPane.showConfirmDialog(this, "Do you want to update?", "Confirm",
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
                        definitionField.setText("");
                        loadDataIntoTable(dictionary);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Old value doesn't match anything of new key to update");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This key is not exist to update");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please try again");
            }

        }

    }

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int selectedIndex = slangTable.getSelectedRow();
        if (dictionary.size() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Nothing here to update");
        } else if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this,
                    "Let's pick some slang to delete");
        } else if (slangField.getText().equals("") || definitionField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter full information");
        } else {
            try {
                String slang = slangField.getText().toString();
                String newDefinition = definitionField.getText().toString();
                String oldSlang = model.getValueAt(selectedIndex, 1).toString();
                String oldDefinition = model.getValueAt(selectedIndex, 2).toString();
                int ret = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Confirm",
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
                        definitionField.setText("");
                        loadDataIntoTable(dictionary);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "This value doesn't match anything to delete");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This key is not exist to delete");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please try again");
            }

        }
    }

    public void randomSlang() {
        Random random = new Random();
        int randomIndex = random.nextInt(keySet.size());
        String key = keySet.get(randomIndex);
        String defini = dictionary.get(key).get(0);
        String randomWord = key + ": " + defini;
        randomLabel.setText(randomWord);
    }

    private void randomButtonMouseClicked(java.awt.event.MouseEvent evt) {
        randomSlang();
    }

    public static void createAndShowGUI() throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Slang Words Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        App newContentPane = new App();
        newContentPane.setOpaque(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // newContentPane.save();
            }
        });

        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        App.createAndShowGUI();
    }
}
