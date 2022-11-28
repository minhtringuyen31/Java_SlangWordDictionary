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

public class UI extends JPanel {
        String appTitle = "SLANG WORDS DICTIONARY";

        JPanel featurePanel;

        JPanel headerPane;
        JLabel titleLabel;
        JPanel searchPanel;
        JLabel searchLabel;
        JTextField searchField;
        JComboBox searchType;
        JButton searchButton;
        JButton historyButton;
        JPanel tablePanel;
        JScrollPane tableScrollPanel;
        JTable slangTable;
        JPanel resetPanel;
        JButton resetButton;
        JPanel leftPanel;
        JPanel randomPanel;
        JButton randomButton;
        JLabel randomLabel;
        JPanel detailPanel;
        JPanel inputDetailPanel;
        JLabel slangLabel;
        JTextField slangField;
        JLabel definitionLabel;
        JTextField definitionField;
        JPanel addEditPanel;
        JButton addButton;
        JButton editButton;
        JButton deleteButton;

        public UI() {
                featurePanel = new JPanel();

                headerPane = new JPanel();
                titleLabel = new JLabel();
                searchPanel = new JPanel();
                searchLabel = new JLabel();
                searchField = new JTextField();
                searchType = new JComboBox<>();
                searchButton = new JButton();
                historyButton = new JButton();
                tablePanel = new JPanel();
                tableScrollPanel = new JScrollPane();
                slangTable = new JTable();
                resetPanel = new JPanel();
                resetButton = new JButton();
                leftPanel = new JPanel();
                randomPanel = new JPanel();
                randomButton = new JButton();
                randomLabel = new JLabel();
                detailPanel = new JPanel();
                inputDetailPanel = new JPanel();
                slangLabel = new JLabel();
                slangField = new JTextField(15);
                definitionLabel = new JLabel();
                definitionField = new JTextField(15);
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
                featurePanel.setBorder(new CompoundBorder(new TitledBorder("Name"), new EmptyBorder(5, 5, 5, 5)));
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

        private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_searchFieldActionPerformed
                // TODO add your handling code here:
        }

        private void searchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_searchComboBoxActionPerformed
                // TODO add your handling code here:
        }

        private void historyButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_historyButtonActionPerformed
                // TODO add your handling code here:
        }

        private void historyButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_historyButtonMouseClicked
                // TODO add your handling code here:
                HistoryForm histoFrame = new HistoryForm(history);
        }// GEN-LAST:event_historyButtonMouseClicked

        private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {// GEN-FIRST:event_resetButtonActionPerformed
                dictionary.clear();
                dictionary = new HashMap<String, ArrayList<String>>();
                loadFromTextFile(slangFile);
                try {
                        dic.setDictionary(dictionary);
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataSerialFileName));
                        oos.writeObject(dic);

                        oos.close();
                } catch (Exception exp) {
                        // System.out.println("can't reset serialize");
                }
                // }
                loadDataIntoTable(dictionary);
        }// GEN-LAST:event_resetButtonActionPerformed

        private void randomButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_randomButtonActionPerformed
                // TODO add your handling code here:
        }

        private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addButtonActionPerformed
                // TODO add your handling code here:
        }

        private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_editButtonActionPerformed
                // TODO add your handling code here:
        }

        private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteButtonActionPerformed
                // TODO add your handling code here:
        }

        private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_playButtonActionPerformed
                // TODO add your handling code here:
        }

        private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nextButtonActionPerformed
                // TODO add your handling code here:
        }

        private void slangTableMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_slangTableMouseClicked
                selectedIndex = slangTable.getSelectedRow();

                // idField.setText(model.getValueAt(selectedIndex, 0).toString());
                slangField.setText(model.getValueAt(selectedIndex, 1).toString());
                definiField.setText(model.getValueAt(selectedIndex, 2).toString());
                // gpaField.setText(model.getValueAt(selectedIndex, 3).toString());
                // addressField.setText(model.getValueAt(selectedIndex, 4).toString());
                // imageField.setText(model.getValueAt(selectedIndex, 5).toString());
                // noteField.setText(model.getValueAt(selectedIndex, 6).toString()); // TODO add
                // your handling code here:
        }// GEN-LAST:event_slangTableMouseClicked

        private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_searchButtonMouseClicked
                // TODO add your handling code here:

                if (searchField.getText().equals("")) {
                        JOptionPane.showMessageDialog(this, "Please enter something to search");
                } else {
                        try {
                                String keyword = searchField.getText().toString();
                                String typeToSearch = (String) searchComboBox.getSelectedItem();
                                HashMap<String, ArrayList<String>> results = new HashMap<String, ArrayList<String>>();
                                if (typeToSearch.equals("Definition")) {
                                        results = searchByDefinition(keyword);
                                        loadDataIntoTable(results);
                                } else if (typeToSearch.equals("Slang")) {
                                        results = searchByKeySlang(keyword);
                                        loadDataIntoTable(results);
                                }

                        } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "Please Enter full information or valid field");
                        }
                }

        }// GEN-LAST:event_searchButtonMouseClicked

        private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_searchFieldKeyReleased
                // TODO add your handling code here:
                if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if (searchField.getText().equals("")) {
                                loadDataIntoTable(dictionary);
                        }
                }
        }// GEN-LAST:event_searchFieldKeyReleased

        private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addButtonMouseClicked
                // TODO add your handling code here:
                if (slangField.getText().equals("") || definiField.getText().equals("")) {
                        JOptionPane.showMessageDialog(this, "Please Enter full information");
                } else {
                        try {
                                String slang = slangField.getText().toString();
                                String definition = definiField.getText().toString();

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
                                definiField.setText("");
                                loadDataIntoTable(dictionary);
                        } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "There are some error, please try again");
                        }
                }

        }// GEN-LAST:event_addButtonMouseClicked

        private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_editButtonMouseClicked
                // TODO add your handling code here:
                selectedIndex = slangTable.getSelectedRow();
                if (dictionary.size() == 0) {
                        JOptionPane.showMessageDialog(this,
                                        "Nothing here to update");
                } else if (selectedIndex == -1) {
                        JOptionPane.showMessageDialog(this,
                                        "Let's pick some slang to update");
                } else if (slangField.getText().equals("") || definiField.getText().equals("")) {
                        JOptionPane.showMessageDialog(this, "Please enter full information");
                } else {
                        try {
                                String slang = slangField.getText().toString();
                                String newDefinition = definiField.getText().toString();
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
                                                definiField.setText("");
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

        }// GEN-LAST:event_editButtonMouseClicked

        private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_deleteButtonMouseClicked
                // TODO add your handling code here:
                selectedIndex = slangTable.getSelectedRow();
                if (dictionary.size() == 0) {
                        JOptionPane.showMessageDialog(this,
                                        "Nothing here to update");
                } else if (selectedIndex == -1) {
                        JOptionPane.showMessageDialog(this,
                                        "Let's pick some slang to delete");
                } else if (slangField.getText().equals("") || definiField.getText().equals("")) {
                        JOptionPane.showMessageDialog(this, "Please enter full information");
                } else {
                        try {
                                String slang = slangField.getText().toString();
                                String newDefinition = definiField.getText().toString();
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
                                                definiField.setText("");
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
        }// GEN-LAST:event_deleteButtonMouseClicked

        public void randomSlang() {
                Random random = new Random();
                int randomIndex = random.nextInt(keySet.size());
                String key = keySet.get(randomIndex);
                String defini = dictionary.get(key).get(0);
                String randomWord = key + ": " + defini;
                randomLabel.setText(randomWord);
        }

        private void randomButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_randomButtonMouseClicked
                // TODO add your handling code here:
                // Random random = new Random();
                // int randomIndex = random.nextInt(keySet.size());
                // String key = keySet.get(randomIndex);
                // String defini = dictionary.get(key).get(0);
                // String randomWord = key + ": " + defini;
                // randomLabel.setText(randomWord);
                randomSlang();
        }// G

        public static void createAndShowGUI() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JFrame frame = new JFrame("Slang Words Dictionary");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // UI.setOpaque(true);

                // frame.addWindowListener(new WindowAdapter() {
                // @Override
                // public void windowClosing(WindowEvent windowEvent) {
                // ui.save();
                // }
                // });

                JComponent newContentPane = new UI();
                newContentPane.setOpaque(true);
                frame.setContentPane(newContentPane);
                frame.setSize(1000, 900);
                // frame.pack();
                frame.setVisible(true);
                // frame.setResizable(false);
        }

        public static void main(String[] args) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                createAndShowGUI();
                        }
                });
        }
}
