import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String word, String[] pages) {
        double[] score = new double[pages.length]; // 각 페이지의 점수
        Map<String, Integer> m = new HashMap<>(); // url, 번호(0부터 시작)
        
        for(int i=0; i<pages.length; i++){
            String cur = pages[i];
            
            Pattern pattern = Pattern.compile("content=\"([^\"]+)\""); // 현재 페이지 url 추출
            Matcher matcher = pattern.matcher(cur);

            while (matcher.find()) {
                String url = matcher.group(1);
                //System.out.println(url);
                m.put(url, i); // 해당 url을 idx라는 번호로 매핑
            }
        }
        
        for(int i=0; i<pages.length; i++){
            String cur = pages[i];
            
            Pattern pattern = Pattern.compile("<body>(.*?)</body>", Pattern.DOTALL); // 현재 페이지 텍스트 추출
            Matcher matcher = pattern.matcher(cur);
            
            int default_score = 0; // 기본 점수
            while (matcher.find()) {
                String text = matcher.group(1);
                text = text.replaceAll("(?is)<[^>]+>", " ");
                
                //System.out.println(text);
                
                Pattern pt = Pattern.compile("(?i)(?<![a-z])" + word + "(?![a-z])");
                // 현재 text에서 word가 몇번 등장하는가
                Matcher mc = pt.matcher(text);
                
                
                while (mc.find()) {
                    default_score++;
                }
                
                score[i] += default_score; // 기본점수 더하기

                //System.out.println("등장 횟수: " + cnt);
                
            }
            
            pattern = Pattern.compile("<a\\s*href=\"([^\"]+)\""); // 현재 페이지 외부링크 추출
            matcher = pattern.matcher(cur);
            
            // for (String key : m.keySet()) {
            //     System.out.println("key = " + key + ", value = " + m.get(key));
            // }
            
            ArrayList<String> out = new ArrayList<>(); // 외부 링크 목록
            while (matcher.find()) {
                out.add(matcher.group(1));   // 수집
            }
            int count = out.size();          // 현재 페이지에 있는 외부 링크들의 총 개수

            for (String url : out) {
                if(m.get(url) != null){
                    int tmp = m.get(url); // 내가 가리키는 url의 인덱스 번호 가져오기
                    
                    if(count == 0) continue;
                    score[tmp] += default_score / (double) count; // 현재 페이지의 기본점수 / 외부링크 수의 총합
                }
                
                // System.out.println(url);
            }
            
        }
        
        int ans = 0;
        double maxScore = score[0];
        for(int i=0; i<score.length; i++){
            if(maxScore < score[i]){
                ans = i;
                maxScore = score[i];
            }
        }
        
        return ans;
    }
}