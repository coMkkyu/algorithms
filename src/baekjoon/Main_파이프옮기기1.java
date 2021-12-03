import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @since 21. 12. 3
 *
 * 백준 17070 파이프옮기기1 - https://www.acmicpc.net/problem/17070
 * dfs를 활용해서 아래, 오른쪽, 오른쪽 대각선 방향에 대해서 끝에 파이프를 옮길 수 있는 모든 경우의 수를 구함.
 *
 */
public class Main_파이프옮기기1 {

    private static int[][] map; // 집의 상태 (0: 빈칸, 1: 벽)
    private static int N, count; // N: 집의 크기, count: 파이프 이동시킬 수 있는 방법의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 집의 크기
        StringTokenizer st = null;
        map = new int[N][N];

        for (int i = 0; i < N; i++) { // init map
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 0);

        System.out.println(count);
    }

    /**
     * 재귀를 통해서 각각의 방향에 대해서 파이프가 이동할 수 있는 방법으로 모두 이동시킨다.
     * 파이프가 목적지에 도달한 경우에만 count 를 증가시킨다.
     *
     * @param r 현재 파이프의 끝부분 행
     * @param c 현재 파이프의 끝부분 열
     * @param dir 현재 파이프가 놓여있는 방향 (0: 가로, 1: 세로, 2: 대각선)
     */
    private static void dfs(int r, int c, int dir) {
        // 끝 지점에 파이프가 도달할 경우 count 증가
        if (r == N - 1 && c == N - 1) {
            count++;
            return;
        }

        // 현재 (r,c) 칸에 있는 파이프가 가로 방향이라면
        if (dir == 0) {
            // 가로방향에서 가능한 이동 방법은 총 2가지
            // #1. 가로로 그대로 이동
            if (isInside(r, c+1) && isMove(r, c+1)) dfs(r, c+1, 0);
            // #2. 대각선으로 이동
            if (isInside(r+1, c+1) && isMove(r, c+1) && isMove(r+1, c) && isMove(r+1, c+1)) dfs(r+1, c+1, 2);
        } else if (dir == 1) { // 현재 (r,c) 칸에 있는 파이프가 세로 방향이라면
            // 세로방향에서 가능한 이동 방법은 총  2가지
            // #1. 세로로 그대로 이동
            if (isInside(r+1, c) && isMove(r+1, c)) dfs(r+1, c, 1);
            // #2. 대각선으로 이동
            if (isInside(r+1, c+1) && isMove(r, c+1) && isMove(r+1, c) && isMove(r+1, c+1)) dfs(r+1, c+1, 2);
        } else if (dir == 2) { // 현재 (r,c) 칸에 있는 파이프가 대각선 방향이라면
            // 대각선방향에서 가능한 이동 방법은 총 3가지
            // #1. 가로로 이동
            if (isInside(r, c+1) && isMove(r, c+1)) dfs(r, c+1, 0);
            // #2. 세로로 이동
            if (isInside(r+1, c) && isMove(r+1, c)) dfs(r+1, c, 1);
            // #3. 대각선으로 그대로 이동
            if (isInside(r+1, c+1) && isMove(r, c+1) && isMove(r+1, c) && isMove(r+1, c+1)) dfs(r+1, c+1, 2);
        }
    }

    // (r,c) 칸이 map 의 범위를 벗어났는지 판별하는 메서드
    private static boolean isInside(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    // (r,c) 칸이 빈칸인지 판별하는 메서드
    private static boolean isMove(int r, int c) {
        return map[r][c] == 0;
    }
}
