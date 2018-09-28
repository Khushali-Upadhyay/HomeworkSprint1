//Name:- Khushali Mukeshbhai Upadhyay
//Date:-23 rd September 2018
//Homework Sprint 1
//Taken help from Jenzel Arevalo





import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileWCCounter {
     static final String PRINT_ALL = "-a";
     static final String LINE_COUNT = "-l";
     static final String WORD_COUNT = "-w";
     static final String CHAR_COUNT = "-c";

    public static class WCFile {
        private int wordCounter;
        private int lineCounter;
        private int charCounter;
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
            if(lineCounter == 0)
                ++lineCounter;
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
            if (isSpecifier(firstElement)) {
                String[] modifiedList = new String[args.length-1];
                transferWCFiles(args, modifiedList);
                processData(modifiedList,wcc);
                printResults(firstElement,wcc);
            } else {
                printInstructions();
            }
        }
        else {
            printInstructions();
        }
    }
    public static void printResults(String firstElement,ArrayList<WCFile> wcc)
    {
        long totalLineCount, totalWordCount, totalCharCount;
        totalLineCount =  totalWordCount =  totalCharCount = 0;
        if (firstElement.equalsIgnoreCase(LINE_COUNT)){
            for(int i=0;i<wcc.size();i++){
                totalLineCount+= wcc.get(i).getLineCounter();
                System.out.println(wcc.get(i).getLineCounter()+"\t" + wcc.get(i).getFileName());
            }
            if(wcc.size()>1) {
                System.out.println(totalLineCount+"\ttotal");
            }
        }
        else if (firstElement.equalsIgnoreCase(WORD_COUNT)) {
            for (int i = 0; i < wcc.size(); i++) {
                totalWordCount += wcc.get(i).getWordCounter();
                System.out.println(wcc.get(i).getWordCounter() + "\t" + wcc.get(i).getFileName());
            }
            if (wcc.size() > 1) {
                System.out.println(totalWordCount + "\ttotal");
            }
        }

        else if (firstElement.equalsIgnoreCase(CHAR_COUNT)) {
            for (int i = 0; i < wcc.size(); i++) {
                totalCharCount += wcc.get(i).getCharCounter();
                System.out.println(wcc.get(i).getCharCounter() + "\t" + wcc.get(i).getFileName());
            }
            if (wcc.size() > 1) {
                System.out.println(totalCharCount + "\ttotal");
            }
        }
        else{
            for(int i=0;i<wcc.size();i++)
            {
                totalLineCount+= wcc.get(i).getLineCounter();
                totalWordCount += wcc.get(i).getWordCounter();
                totalCharCount += wcc.get(i).getCharCounter();
                System.out.println(wcc.get(i).getLineCounter()+"\t"+wcc.get(i).getWordCounter()+"\t"+wcc.get(i).getCharCounter() + "\t" + wcc.get(i).getFileName());


            }
        }

    }
    public static Boolean isSpecifier(String firstElement)
    {
        if (firstElement.equalsIgnoreCase(LINE_COUNT) || firstElement.equalsIgnoreCase(WORD_COUNT) || firstElement.equalsIgnoreCase(CHAR_COUNT))
            return true;
        else {
            return false;
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