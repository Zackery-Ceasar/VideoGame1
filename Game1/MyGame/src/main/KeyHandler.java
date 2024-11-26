package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public boolean checkDrawTime = false;

    public boolean shiftPressed;

    public boolean enterPressed;

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
     this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
          int code = e.getKeyCode();

          //TITLE STATE

          if (gp.gameState == gp.titleState) {

               if(code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
               }
     
               if(code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2) {
                         gp.ui.commandNum = 0;
                     }
               }

               if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                         gp.gameState = gp.playState;
                    }
                    if (gp.ui.commandNum == 1) {
                         //add later
                    }
                    if(gp.ui.commandNum == 2) {
                         System.exit(0);
                    }
               }

          }

          if (gp.gameState == gp.playState) {

               if(code == KeyEvent.VK_W) {
               upPressed = true;
               }

               if(code == KeyEvent.VK_S) {
                    downPressed = true;
               }

               if(code == KeyEvent.VK_A) {
                    leftPressed = true;
               }

               if(code == KeyEvent.VK_D) {
                    rightPressed = true;
               }

               if(code == KeyEvent.VK_ESCAPE) {

                    gp.gameState = gp.pauseState;

               }

               if(code == KeyEvent.VK_T) {
                    if (checkDrawTime == false) {
                         checkDrawTime = true;
                    } else if (checkDrawTime == true) {
                         checkDrawTime = false;
                    }
               }

               if(code == KeyEvent.VK_SHIFT) {
                    shiftPressed = true;
               }

               if(code == KeyEvent.VK_ENTER) {
                    enterPressed = true;
               }

          }

          // PAUSE STATE
          else if(gp.gameState == gp.pauseState) {
               if(code == KeyEvent.VK_ESCAPE) {
               
                    gp.gameState = gp.playState;
                    
               }
               if(code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 1;
                    }
               }
     
               if(code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 1) {
                         gp.ui.commandNum = 0;
                     }
               }

               if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                         gp.gameState = gp.playState;
                    }
                    if (gp.ui.commandNum == 1) {
                         gp.gameState = gp.titleState;
                    }
                    
               }
          }

          //DIALOGUE STATE

          else if(gp.gameState == gp.dialogueState) {
               if(code == KeyEvent.VK_ENTER) {
                    //System.out.println(gp.npc[gp.numMap][gp.player.currentNPC].dialogueIndex);
                    

                    // NPC DIALOGUE
                    
                    if (gp.player.currentNPC != 999) {
                              gp.npc[gp.numMap][gp.player.currentNPC].dialogueIndex++;
                         if (gp.npc[gp.numMap][gp.player.currentNPC].dialogues[gp.npc[gp.numMap][gp.player.currentNPC].dialogueIndex] != null) {
                              
                              gp.npc[gp.numMap][gp.player.currentNPC].speak();
                         } else {
                              gp.gameState = gp.playState;
                         }

                         // DEFAULT
                    } else {
                         gp.gameState = gp.playState;
                    }
                    

                    
                    //enterPressed = true;
               }
          }


       
     

    }

    @Override
    public void keyReleased(KeyEvent e) {
       int code = e.getKeyCode();

       if(code == KeyEvent.VK_W) {
            upPressed = false;
       }
       if(code == KeyEvent.VK_S) {
            downPressed = false;
       }
       if(code == KeyEvent.VK_A) {
            leftPressed = false;
       }
       if(code == KeyEvent.VK_D) {
            rightPressed = false;
       }
       if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
       }


     if(code == KeyEvent.VK_ENTER) {
           enterPressed = false;
     }




    }




}
