package langUtil.cellMachine;

import langUtil.cellMachine.instr.*;

public class TestStackMachine {

      StackMachine sm;
      public TestStackMachine(){
         sm = new StackMachine(5,26);
         
         sm.addInstr(new PushI(0));   
         sm.addInstr(new PushI(10));
         sm.addInstr(new Save(0));
         sm.addInstr(new Save(1));
         sm.addInstr(new Label("Inicio",new Load(1)));
         sm.addInstr(new Load(0) );
         sm.addInstr(new LtI()   );
         sm.addInstr(new JumpF("Fim") );
         
         sm.addInstr(new PushC('\n') );
         sm.addInstr(new Load(1) );
         sm.addInstr( new PrintI() );
         sm.addInstr( new PrintC() );
         
         sm.addInstr(new Load(1) );
         sm.addInstr(new PushI(1) );
         sm.addInstr(new AddI() );
         sm.addInstr(new Save(1) );
         
         sm.addInstr(new Jump("Inicio") );
         
         sm.addInstr(new Label("Fim",new PushC('M')));
         sm.addInstr( new PushC('I') );
         sm.addInstr( new PushC('F') );
         sm.addInstr( new PrintC() );
         sm.addInstr( new PrintC() );
         sm.addInstr( new PrintC() );
         sm.addInstr( new Halt() );
         sm.labelProcess();
         sm.run();
      }
      
      public static void main(String args[]){
           TestStackMachine m = new TestStackMachine(); 
      }

       
}
