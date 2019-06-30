package pe.edu.cibertec.dependencyinyection;

public class AppPoolWacher {
   INotificationAction action = null;


    public INotificationAction getAction() {
        return action;
    }

    public void setAction(INotificationAction action) {
        this.action = action;
    }

    //Inyeccion del constructor
   public AppPoolWacher(INotificationAction action)
   {
       this.action = action;
   }


    public AppPoolWacher()
    {
        //this.action = action;
    }

   //Inyeccion de metodo



//    public void notify(String message)
//    {
//        if(action == null)
//        {
//            //action = new EventLogWriter();
//            //action = new SmsSender();
//            action = new EmailSender();
//            //eventLogWriter = new EventLogWriter();
//        }
//
//        action.write(message);
//    }



//    public void notify(INotificationAction action,String message)
//    {
//        this.action = action;
//        this.action= message;
//    }

    public  void notify( String message)
    {
        action.write(message);
    }

    public void notify(INotificationAction actions,String message)
    {
        this.action = actions;
        this.action.write(message);
    }
}
