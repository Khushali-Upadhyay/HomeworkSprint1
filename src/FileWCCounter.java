import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileWCCounter {

    public static class WCFile {
        private int wordCounter;
        private int lineCounter;
        private long charCounter;
        private String Content;
        private String fileName;

        public int getLineCounter() {
            return lineCounter;
        }

        public void setLineCounter() {
            for (int i = 0; i < getContent().length(); i++) {
                char c = getContent().charAt(i);
                if (c == '\n') {
                    lineCounter++;
                }
            }
        }

        public int getWordCounter()
        {
            return wordCounter;
        }

        public void setWordCounter(){
            String[] words = getContent().trim().split("\\s+");
            this.wordCounter = words.length;
        }

        public long getCharCounter() {
            return charCounter;
        }

        public void setCharCounter() {
            this.charCounter = getContent().length();
        }

        public String getFileName() {
            return fileName;
        }


        public void setFileName(String fileName) {
            this.fileName = fileName;

        }

        public WCFile(String fileName) {
            setFileName(fileName);

        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content)
        {
            this.Content = Content;
        }



        public void initializeCounts()
        {
            setLineCounter();
            setWordCounter();
            setCharCounter();
        }
    }
    public static void main(String[] args) throws IOException {
        ArrayList<WCFile> wcc = new ArrayList<WCFile>();

        if (args.length > 0) {
            String firstElement = args[0];
            switch (firstElement){

                case "-l" :
                    String[] modifiedList = new String[args.length-1];
                    transferWCFiles(args,modifiedList);
                    processData(modifiedList,wcc);
                    int total = 0;
                    for (int i=0;i<wcc.size();i++)
                    {
                        total+=wcc.get(i).getLineCounter();
                        System.out.println(" "+wcc.get(i).getLineCounter());

                    }
                    if(wcc.size()>1){
                        System.out.println(" "+total);
                    }
                    break;

                case "-w":
                    String[] modifiedList1 = new String[args.length-1];
                    transferWCFiles(args,modifiedList1);
                    processData(modifiedList1,wcc);
                    int total1 = 0;
                    for (int i=0;i<wcc.size();i++)
                    {
                        total1+=wcc.get(i).getWordCounter();
                        System.out.println(" "+wcc.get(i).getWordCounter());

                    }
                    if(wcc.size()>1){
                        System.out.println(" "+total1);
                    }
                    break;

                case "-c":
                    String[] modifiedList2 = new String[args.length-1];
                    transferWCFiles(args,modifiedList2);
                    processData(modifiedList2,wcc);
                    int total2 = 0;
                    for (int i=0;i<wcc.size();i++)
                    {
                        total2+=wcc.get(i).getCharCounter();
                        System.out.println(" "+wcc.get(i).getCharCounter());

                    }
                    if(wcc.size()>1){
                        System.out.println(" "+total2);
                    }
                    break;
                    default:
                        String[] modifiedList3 = new String[args.length-1];
                        transferWCFiles(args,modifiedList3);
                        processData(modifiedList3,wcc);
                        int ltotal = 0;
                        int wtotal = 0;
                        int ctotal = 0;
                        for (int i=0;i<wcc.size();i++)
                        {
                            ltotal+=wcc.get(i).getLineCounter();
                            wtotal+=wcc.get(i).getWordCounter();
                            ctotal+=wcc.get(i).getCharCounter();
                            System.out.println(" "+wcc.get(i).getLineCounter()+" "+wcc.get(i).getWordCounter()+" "+wcc.get(i).getCharCounter());

                        }
                        if(wcc.size()>1){
                            System.out.println(" "+ltotal+" "+wtotal+" "+ctotal);
                        }

                        break;
            }
        }
        else{
                printInstructions();
        }

    }

        public static void printInstructions()
        {
            System.out.println("Instructions:");
            System.out.println("wc will print instructions for how to use wc");
            System.out.println("wc -l <filename> will print the line count of a file");
            System.out.println("wc -c <filename> will print the character count");
            System.out.println("wc -w <filename> will print the word count");
            System.out.println("wc <filename> will print all of the above");

        }






        public static void transferWCFiles(String[] args,String[] modifiedList)
        {
            for(int i=1;i<args.length;i++){
                 modifiedList[i-1] = args[i];
            }
        }

    public static void processData(String[] args,ArrayList<WCFile> wcc) throws IOException {
        for (int i=0;i<args.length;i++){
            WCFile newFile = new WCFile(args[i]);
            List<String> line = Files.readAllLines(Paths.get(args[i]));
            String message = "";
            for (String l : line){
                message+=l;
                message+="\n";
            }
            message=message.substring(0,message.length()-1);
            newFile.setContent(message);
            newFile.initializeCounts();
            wcc.add(newFile);

    }
}
}