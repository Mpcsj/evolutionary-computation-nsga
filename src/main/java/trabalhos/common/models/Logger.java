package trabalhos.common.models;

public class Logger {
    private int defaultLogLevel;
    private String persistFilePath,tag;
    public Logger (String tag,int defaultLogLevel,String persistFilePath){
        this.defaultLogLevel = defaultLogLevel;
        this.persistFilePath = persistFilePath;
        this.tag = tag;
    }
    public Logger(String tag ,int defaultLogLevel){
        this(tag,defaultLogLevel,null);
    }
    public Logger(String tag){
        this(tag,0,null);
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
            System.out.println("["+tag+"] -> "+message);
        }
        if(persist && persistFilePath!=null){
            // TODO: persisto o arquivo no path do file indicado ...
        }
    }
}
