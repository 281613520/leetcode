package daily;

public class TreeAncestor {
    int[][] pa;
    public TreeAncestor(int n, int[] parent) {
        int m = 32 - Integer.numberOfLeadingZeros(n);
        pa = new int[n][m];

        for (int i = 0 ; i < n ; i++){
            pa[i][0] = parent[i];
        }

        // dp[x][i] = dp[dp[x][i-1]][i-1]
        // 先填满每一层的结果
        // 再算下一层的每一个节点的父节点
        for (int i = 0 ; i < m-1 ; i++){
            for (int x = 0 ; x < n ; x++){
                int p  = pa[x][i];
                pa[x][i+1] = p < 0? -1 : pa[p][i];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int m = 32 - Integer.numberOfLeadingZeros(k); // k 的二进制长度
        for (int i = 0; i < m; i++) {
            if (((k >> i) & 1) > 0) { // k 的二进制从低到高第 i 位是 1
                node = pa[node][i];
                if (node < 0) break;
            }
        }
        return node;
    }
}
