package Trie;

import java.util.HashMap;
import java.util.Map;

//https://codingnojam.tistory.com/40 참고
public class Trie_implementation {

    static class Node{
        //Map의 키 값에는 문자를 이루는 각각의 단어들이 들어가고, 키와 매핑되는 값에는 자식노드 클래스가 저장된다.
        Map<Character, Node> childedNode = new HashMap<Character, Node>();

        //문자가 현재 trie내부에 존재하는지 체크하기 위한 boolean변수
        boolean endOfword;
    }

    static class Trie{

        //trie자료구조를 생성할 때 rootNode는 기본적으로 생성
        Node rootNode = new Node();

        //trie에 문자열 저장
        void insert(String str){
            Node node = this.rootNode;

            //문자열의 각 단어마다 가져와서 자식노드 중에 있는지 체크
            //없으면 자식노드 새로 생성
            for(int i=0; i<str.length(); i++){
                //computeIfAbsent -> 첫번째 인자에 넣은 key값이 없으면, 두번째 인자에 넣은 람다식을 수행하고 나온 결과값 반환
                //첫번째 인자에 넣은 key값이 있다면, key에 해당하는 값 반환
                node = node.childedNode.computeIfAbsent(str.charAt(i), key -> new Node());
            }
            //저장할 문자열의 마지막 단어에 매핑되는 노드에 단어의 끝임을 명시
            node.endOfword = true;
        }

        //trie에 문자열 검색
        boolean search(String str){
            //trie자료구조는 항상 rootNode부터 시작
            Node node = this.rootNode;

            //문자열의 각 단어마다 노드가 존재하는지 체크
            for(int i=0; i<str.length(); i++){
                //문자열의 각 단어마다 매핑된 노드가 존재하면 가져오고 아니면 null
                node = node.childedNode.getOrDefault(str.charAt(i),null);
                if(node == null){
                    //node가 null이면 현재 trie에 해당 문자열은 없음
                    return false;
                }
            }
            // 문자열의 마지막 단어까지 매핑된 노드가 존재한다고해서 무조건 문자열어 존재하는게 아님
            // busy를 Trie에 저장했으면, bus의 마지막 s단어에 매핑 된 노드는 존재하지만 Trie에 저장된건 아님
            // 그러므로 현재 노드가 단어의 끝인지 아닌지 체크하는 변수로 리턴
            return node.endOfword;
        }
    }
    public static void main(String[] args){
        // Trie 자료구조 생성
        Trie trie = new Trie();

        // Trie에 문자열 저장
        trie.insert("kakao");
        trie.insert("busy");
        trie.insert("card");
        trie.insert("cap");

        // Trie에 저장 된 문자열 확인
        System.out.println(trie.search("bus"));		// false
        System.out.println(trie.search("busy"));    // true
        System.out.println(trie.search("kakao"));   // true
        System.out.println(trie.search("cap"));     // true
    }
}

