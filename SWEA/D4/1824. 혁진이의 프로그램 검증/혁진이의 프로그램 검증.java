import java.io.*;
import java.util.*;
 
public class Solution {
	static int[] di={-1,1,0,0};//상0,하1,좌2,우3
    static int[] dj={0,0,-1,1};
    static int R,C,mem;
    static char[][] map;
    static boolean[][][][] v;
    static String ans;
    static boolean end;    
    
    static void bfs(int i,int j,int dir){
    	ArrayDeque<int[]> q=new ArrayDeque<>();
    	q.offer(new int[]{i,j,dir,0});
    	while(!q.isEmpty()){
    		int[] ij=q.poll();
    		i=ij[0]; j=ij[1]; dir=ij[2]; int mem=ij[3];
    		
    		if(i<0 ) i=R-1; if(j<0)  j=C-1;
    		if(i>=R) i=0;   if(j>=C) j=0;
    		
    		if(v[i][j][dir][mem]) continue;
    		v[i][j][dir][mem]=true;
    		switch(map[i][j]){
    			case '<': dir=2; break;//좌2
    			case '>': dir=3; break;//우3
    			case '^': dir=0; break;//상0
    			case 'v': dir=1; break;//하1
    			case '_': dir=mem==0? 3:2; break;//우3:좌2
    			case '|': dir=mem==0? 1:0; break;//하1:상0
    			case '?': break;//밑에서 4방으로
    			case '.': break;
    			case '@': ans="YES"; return;//정답
    			case '+': mem=(mem==15? 0:mem+1); break;
    			case '-': mem=(mem==0? 15:mem-1); break;
    			default : mem=map[i][j]-'0'; break; // '0'~'9' -> int
    		}
    		if(map[i][j]=='?'){
    			for(int d=0; d<4; d++){
    				int ni=i+di[d];
    				int nj=j+dj[d];
    				q.offer(new int[]{ni,nj,d,mem});
    			}
    		}else{
    			int ni=i+di[dir];
    			int nj=j+dj[dir];
    			q.offer(new int[]{ni,nj,dir,mem});
    		}
    	}
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	st=new StringTokenizer(br.readLine()," ");
        	R=Integer.parseInt(st.nextToken());
        	C=Integer.parseInt(st.nextToken());
        	map=new char[R][C];
        	v=new boolean[R][C][4][16];
        	end=false;
        	for(int i=0; i<R; i++){
        		String s=br.readLine();
        		for(int j=0; j<C; j++){
        			map[i][j]=s.charAt(j);
        			if(map[i][j]=='@') end=true;
        		}
        	}
        	mem=0; ans="NO";
        	if(end) bfs(0,0,3);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }    
}