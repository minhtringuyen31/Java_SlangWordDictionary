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
import java.awt.event.KeyListener;
import java.awt.image.AreaAveragingScaleFilter;
import javax.swing.table.DefaultTableModel;
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

                searchField.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // searchFieldActionPerformed(evt);
                        }
                });
                searchField.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                // searchFieldKeyReleased(evt);
                        }
                });

                searchType.setModel(new DefaultComboBoxModel<>(new String[] { "Slang", "definitiontion" }));
                searchType.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // searchComboBoxActionPerformed(evt);
                        }
                });

                searchButton.setText("Search");
                searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                // searchButtonMouseClicked(evt);
                        }
                });

                historyButton.setText("History");
                historyButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                // historyButtonMouseClicked(evt);
                        }
                });
                historyButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // historyButtonActionPerformed(evt);
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
                slangTable.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                // slangTableMouseClicked(evt);
                        }
                });
                tableScrollPanel.setViewportView(slangTable);
                if (slangTable.getColumnModel().getColumnCount() > 0) {
                        slangTable.getColumnModel().getColumn(0).setResizable(false);
                }

                resetButton.setText("Reset Default Dictionary");
                resetButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // try {
                                // // resetButtonActionPerformed(evt);
                                // } catch (IOException e) {
                                // e.printStackTrace();
                                // }
                        }
                });
                resetPanel.add(resetButton);
                // End: dictionary Panel

                // Start: Feature Panel
                randomButton.setText("Random Slang");
                randomButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                // randomButtonMouseClicked(evt);
                        }
                });
                randomButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // randomButtonActionPerformed(evt);
                        }
                });

                slangLabel.setText("Slang: ");

                definitionLabel.setText("Definition:");

                addButton.setText("Add");
                addButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                // addButtonMouseClicked(evt);
                        }
                });
                addButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // addButtonActionPerformed(evt);
                        }
                });
                addEditPanel.add(addButton);

                editButton.setText("Edit");
                editButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                // editButtonMouseClicked(evt);
                        }
                });
                editButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // editButtonActionPerformed(evt);
                        }
                });
                addEditPanel.add(editButton);

                deleteButton.setText("Delete");
                deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                // deleteButtonMouseClicked(evt);
                        }
                });
                deleteButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // deleteButtonActionPerformed(evt);
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
                // modeComboBox.addActionListener(new java.awt.event.ActionListener() {
                // public void actionPerformed(java.awt.event.ActionEvent evt) {
                // modeComboBoxActionPerformed(evt);
                // }
                // });

                // playButton.setText("Let's Play Quiz Game");
                // playButton.addMouseListener(new java.awt.event.MouseAdapter() {
                // public void mouseClicked(java.awt.event.MouseEvent evt) {
                // playButtonMouseClicked(evt);
                // }
                // });
                // playButton.addActionListener(new java.awt.event.ActionListener() {
                // public void actionPerformed(java.awt.event.ActionEvent evt) {
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
                // aButton.addMouseListener(new java.awt.event.MouseAdapter() {
                // public void mouseClicked(java.awt.event.MouseEvent evt) {
                // aButtonMouseClicked(evt);
                // }
                // });
                // answerPane.add(aButton);

                // bButton.setText("B. ");
                // bButton.setEnabled(false);
                // bButton.addMouseListener(new java.awt.event.MouseAdapter() {
                // public void mouseClicked(java.awt.event.MouseEvent evt) {
                // bButtonMouseClicked(evt);
                // }
                // });
                // answerPane.add(bButton);

                // cButton.setText("C. ");
                // cButton.setEnabled(false);
                // cButton.addMouseListener(new java.awt.event.MouseAdapter() {
                // public void mouseClicked(java.awt.event.MouseEvent evt) {
                // cButtonMouseClicked(evt);
                // }
                // });
                // answerPane.add(cButton);

                // dButton.setText("D. ");
                // dButton.setEnabled(false);
                // dButton.addMouseListener(new java.awt.event.MouseAdapter() {
                // public void mouseClicked(java.awt.event.MouseEvent evt) {
                // dButtonMouseClicked(evt);
                // }
                // });
                // answerPane.add(dButton);

                // stopButton.setText("Stop Game");
                // stopButton.setEnabled(false);
                // stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
                // public void mouseClicked(java.awt.event.MouseEvent evt) {
                // stopButtonMouseClicked(evt);
                // }
                // });
                // jPanel1.add(stopButton);

                // nextButton.setText("Next Question");
                // nextButton.setEnabled(false);
                // nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
                // public void mouseClicked(java.awt.event.MouseEvent evt) {
                // nextButtonMouseClicked(evt);
                // }
                // });
                // nextButton.addActionListener(new java.awt.event.ActionListener() {
                // public void actionPerformed(java.awt.event.ActionEvent evt) {
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

        public static void createAndShowGUI() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JFrame frame = new JFrame("Slang Words Dictionary");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // UI.setOpaque(true);

                // frame.addWindowListener(new java.awt.event.WindowAdapter() {
                // @Override
                // public void windowClosing(java.awt.event.WindowEvent windowEvent) {
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
