/**
 * Task.java		Aug 11, 2016, 9:49:16 PM
 */
package komorebi.clyde.script;

/**
 * 
 * @author Aaron Roy
 * @version 
 */
public class Task {

  public Task(Instructions instruction)
  {
    this.instruction = instruction;
  }
  
  protected Instructions instruction;
  public Instructions getInstruction() 
  {
    return instruction;
  }
  
  public static class TaskWithNumber extends Task
  {
    
    int number;
    
    public TaskWithNumber(Instructions instruction, int arg)
    {
      super(instruction);
      this.number = arg;
    }
    
    public int getNumber(){
      return number;
    }
  }
  
  public static class TaskWithInstructionList extends Task
  {
    InstructionList instructionList;
    public TaskWithInstructionList(Instructions instruction, InstructionList 
        instr)
    {
      super(instruction);
      this.instructionList = instr;
    }
    
    public InstructionList getInstructionList()
    {
      return instructionList;
    }
  }
  
  public static class TaskWithLocation extends Task
  {
    int x, y;
    
    public TaskWithLocation(Instructions instruction, int x, int y)
    {
      super(instruction);
      this.x = x;
      this.y = y;
    }
    
    public int getX(){
      return x;
    }
    
    public int getY(){
      return y;
    }
    
  }
  
  public static class TaskWithNumberAndLocation extends Task
  {
    int num, x, y;
    public TaskWithNumberAndLocation(Instructions instructions, int num,
        int x, int y)
    {
      super(instructions);
      this.num = num;
      this.x = x;
      this.y = y;
    }
    
    public int getX()
    {
      return x;
    }
    
    public int getY()
    {
      return y;
    }
    
    public int getNumber()
    {
      return num;
    }
    
  }
  
  public static class TaskWithString extends Task
  {
    String s;
    
    public TaskWithString(Instructions instruction, String s)
    {
      super(instruction);
      this.s = s;
    }
    
    public String getString()
    {
      return s;
    }
  }
  
  public static class TaskWithStringArray extends Task
  {
    String[] args;
    TaskWithBranch[] branches;
    
    public TaskWithStringArray(Instructions instruction, String[] args, 
        TaskWithBranch[] branches)
    {
      super(instruction);
      this.args = args;
      this.branches = branches;
    }
    
    public String[] getStrings()
    {
      return args;
    }
    
    public TaskWithBranch getTask(String s)
    {
      for (int i=1; i<args.length; i++)
      {
        if (args[i].equals(s))
        {
          return branches[i];
        }
      }
    
    return null;
    }
  }
  
  public static class TaskWithTask extends Task
  {
    Task task;
    int s;
    boolean reverse;
    
    public TaskWithTask(Instructions instruction, Task task, int predicate,
        boolean reverse)
    {
      super(instruction);
      this.task = task;
      s = predicate;
      this.reverse = reverse;
    }
    
    public Task getTask()
    {
      return task;
    }
    
    public int getPredicate()
    {
      return s;
    }
    
    public boolean isReversed()
    {
      return reverse;
    }
  }
  
  public static class TaskWithBoolean extends Task
  {
    boolean ifTrue;
    Task task;
    
    public TaskWithBoolean(Instructions instruction, Task task)
    {
      super(instruction);
      this.ifTrue = false;
      this.task = task;
    }
    
    public void setIfTrue(boolean b)
    {
      this.ifTrue = b;
    }
    
    public boolean ifTrue()
    {
      return ifTrue;
    }
    
    public Task getTask()
    {
      return task;
    }
    
    
  }
  
  public static class TaskWithBranch extends Task
  {
    InstructionList branch;
    
    public TaskWithBranch(Instructions instruction, InstructionList branch)
    {
      super(instruction);
      this.branch = branch;
    }
    
    public TaskWithBranch(Instructions instruction, String s)
    {
      super(instruction);
      this.branch = new InstructionList(s);
    }
    
    public void setBranch(InstructionList branch)
    {
      this.branch = branch;
    }
    
    public InstructionList getBranch()
    {
      return branch;
    }
    
    public void setInstruction(Instructions instruction)
    {
      this.instruction = instruction;
    }
  }


}


