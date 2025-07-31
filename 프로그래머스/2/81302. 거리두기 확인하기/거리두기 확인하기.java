class Solution {
    
    static int[] ans;
    
    static int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, -1, 1, 1, -1, 1, -1};
    
    // 파티션이 있어야 하는 곳(2개씩)
    static int[] px = {0, 1, 0, 1, 0, -1, 0, -1};
    static int[] py = {1, 0, -1, 0, 1, 0, -1, 0};
    
    public int[] solution(String[][] places) {
        
        ans = new int[places.length];
        
        for(int i=0; i<ans.length; i++){
            ans[i] = 1;
        }
        
        for(int i=0; i<places.length; i++){
            
            boolean flag = false;
            for(int x=0; x<5; x++){
                for(int y=0; y<5; y++){
                    
                    if(places[i][x].charAt(y) == 'P'){
                        // 현재 위치에 사람이 있다면 체크
                        
                        // 상하좌우 사람 있는지 체크
                        for(int k=0; k<4; k++){
                            int tx = x + dx[k];
                            int ty = y + dy[k];
                            
                            if(tx<0 || tx>=5 || ty<0 || ty>=5) continue;
                            if(places[i][tx].charAt(ty) == 'P'){
                                ans[i] = 0;
                                flag = true;
                                break;
                            }
                        }
                        
                        if(flag) break;
                        
                        // 2 간격으로 사람이 있는지 체크
                        for(int k=0; k<4; k++){
                            int tx = x + dx[k]*2;
                            int ty = y + dy[k]*2;
                            
                            if(tx<0 || tx>=5 || ty<0 || ty>=5) continue;
                            if(places[i][tx].charAt(ty) == 'P'){
                                
                                // 사이에 파티션 필요
                                int tx2 = x + dx[k];
                                int ty2 = y + dy[k];
                                if(places[i][tx2].charAt(ty2) != 'X'){
                                    ans[i] = 0;
                                    flag = true;
                                    break;
                                }
                                
                                
                            }
                        }
                        
                        if(flag) break;
                        
                        // 대각선으로 사람이 있는지 체크
                        int idx = 0;
                        for(int k=4; k<8; k++){
                            int tx = x + dx[k];
                            int ty = y + dy[k];
                            
                            if(tx<0 || tx>=5 || ty<0 || ty>=5){
                                idx += 2;
                                continue;
                            }
                            if(places[i][tx].charAt(ty) == 'P'){
                                
                                // 사람이 있다면 사이에 파티션 필요
                                for(int p=0; p<2; p++){
                                    int tx2 = x + px[idx+p];
                                    int ty2 = y + py[idx+p];
                                    
                                    if(tx2<0 || tx2>=5 || ty2<0 || ty2>=5) continue;
                                    if(places[i][tx2].charAt(ty2) != 'X'){
                                        ans[i] = 0;
                                        flag = true;
                                        break;
                                    }
                                }
                                
                                if(flag) break;
                            }
                            
                            idx += 2;
                        }
                        
                    }
                    
                    if(flag) break;
                }
                
                if(flag) break;
            }
            
        }
        
        return ans;
    }
}