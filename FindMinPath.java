import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class node2{
    boolean visited = false;
    int index;
    int distance;
    int[] next = new int[3];
    public node2(int n,int index,int l,int r,int q){
        this.index=index;
        this.distance=0;
        next[0]=l;next[1]=r;next[2]=q;
    }
}

public class FindMinPath {
    private static int numOfNode=0;
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int N = input.nextInt();

        node2[] checkPoints = new node2[N+1];
        checkPoints[1] = new node2(N,1,0,2,input.nextInt());
        for(int i=2;i<=N-1;i++){
            checkPoints[i] = new node2(N,i,i-1,i+1,input.nextInt());
        }
        checkPoints[N] = new node2(N,N,N-1,0,input.nextInt());

        Queue<node2> que = new LinkedList<node2>();

        que.add(checkPoints[1]);
        checkPoints[1].visited=true;
        numOfNode++;
        while (numOfNode<N){
            node2 cur = que.remove();
            for(int e : cur.next){
                if(e!=0&&!checkPoints[e].visited){
                    que.add(checkPoints[e]);
                    checkPoints[e].visited=true;
                    checkPoints[e].distance = cur.distance+1;
                    numOfNode++;
                }
            }
        }
        for(int i=1;i<=N;i++){
            out.print(checkPoints[i].distance+" ");
        }
        out.close();
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}

