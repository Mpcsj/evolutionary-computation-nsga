package trabalhos.common.models;

public class Logger {
    private int defaultLogLevel;
    private String persistFilePath;
    public Logger (int defaultLogLevel,String persistFilePath){
        this.defaultLogLevel = defaultLogLevel;
        this.persistFilePath = persistFilePath;
    }
    public Logger(int defaultLogLevel){
        this(defaultLogLevel,null);
    }
    public Logger(){
        this(0,null);
    }
    public void showMessage(String message){
        showMessage(message,defaultLogLevel,0);
    }
    public void showMessage(String message,int showAtLeastLevel){
        showMessage(message,defaultLogLevel,showAtLeastLevel);
    }
    public void showMessage(String message,int currLogLevel,int showAtLeastLevel){
        showMessage(message,currLogLevel,showAtLeastLevel,false);
    }
    public void showMessage(
            String message,
            int currLogLevel,
            int showAtLeastLevel,
            boolean persist){
        if(currLogLevel >= showAtLeastLevel){
            System.out.println(message);
        }
        if(persist && persistFilePath!=null){
            // TODO: persisto o arquivo no path do file indicado ...
        }
    }
}
