import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.PriorityQueue
import java.util.Stack
import java.util.StringTokenizer

const val MAX_VALUE = 200001
val visited = BooleanArray(MAX_VALUE)
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    bfs(st.nextToken().toInt(), st.nextToken().toInt())
}

fun bfs(x: Int, n: Int) {
    val pq = PriorityQueue<Node>()
    val parent = IntArray(MAX_VALUE) { it }
    visited[x] = true
    pq.add(Node(x, 0))

    while(pq.isNotEmpty()) {
        val p = pq.poll()
        if(p.x == n) {
            val sb = StringBuilder()
            val stack = Stack<Int>()
            stack.add(n)
            var parentX = n
            while(true) {
                if(parent[parentX] == parentX) break
                stack.add(parent[parentX])
                parentX = parent[parentX]
            }
            while(stack.isNotEmpty()) {
                sb.append("${stack.pop()} ")
            }
            print("${p.cnt}\n${sb}")
            return
        }

        for(i in 0 .. 2) {
            when(i) {
                0 -> {
                    if(p.x + 1 >= MAX_VALUE) continue
                    if(visited[p.x + 1]) continue
                    visited[p.x + 1] = true
                    pq.add(Node(p.x + 1, p.cnt + 1))
                    parent[p.x + 1] = p.x
                }
                1 -> {
                    if(p.x - 1 < 0) continue
                    if(visited[p.x - 1]) continue
                    visited[p.x - 1] = true
                    pq.add(Node(p.x - 1, p.cnt + 1))
                    parent[p.x - 1] = p.x
                }

                2 -> {
                    if(p.x * 2 >= MAX_VALUE) continue
                    if(visited[p.x * 2]) continue
                    visited[p.x * 2] = true
                    pq.add(Node(p.x * 2, p.cnt + 1))
                    parent[p.x * 2] = p.x
                }

            }
        }
    }
}

data class Node(val x: Int, val cnt: Int): Comparable<Node> {
    override fun compareTo(other: Node): Int = cnt - other.cnt
}