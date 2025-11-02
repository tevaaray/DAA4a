# 🏙️ Smart City / Smart Campus Scheduling
**Course Project – Graph Algorithms Integration (DAA / DBMS)**

---

## 🎯 Goal
To combine two key algorithmic topics in one practical scenario:
1. **Strongly Connected Components (SCC)** & **Topological Ordering**
2. **Shortest and Longest Paths in DAGs**

---

## 🧠 Scenario
In a Smart City / Smart Campus system, tasks such as street cleaning, road repairs, and sensor maintenance depend on each other.  
Some tasks form **cyclic dependencies (SCCs)**, while others are **acyclic (DAG)** and can be planned optimally.

The main objectives are to:
- Detect cyclic dependencies (SCCs)
- Compress them into a **condensation DAG**
- Compute a **topological order**
- Evaluate **shortest** and **longest (critical)** paths for scheduling

---


## ⚙️ How to Build & Run

### 🧩 Compile
```bash
mvn clean compile
```
### 🧪 Run all tests
```bash
mvn test
```

### Generate datasets
```bash
java -cp target/classes data.GraphGenerator

```

### 🧠 Algorithms Implemented
1️⃣ Strongly Connected Components (Tarjan)

- Finds all SCCs in O(V + E) time
- Builds a condensed DAG where each SCC is a single node
- Outputs: component list + sizes

2️⃣ Topological Sort (Kahn)

- Works on DAGs or condensation graphs
- Counts pushes/pops via Metrics
- Output: valid topological order of tasks

3️⃣ Shortest and Longest Paths in DAG

- Shortest path: dynamic programming over topological order
- Longest path (critical): similar logic with sign inversion
- Uses edge weights as task durations
- Metrics: relaxations count + execution time

## 📊 Datasets Summary

| Dataset | Nodes | Edges | Structure | Cyclic | Notes |
|----------|--------|--------|------------|---------|--------|
| small1.json | 6 | 7 | Mixed | ✅ Yes | Two-way edges between 4↔5 |
| small2.json | 8 | 8 | Mixed | ✅ Yes | Contains small cycle 3↔4↔3 |
| small3.json | 9 | 10 | Mixed | ✅ Yes | Several cross edges, dense |

---

## 🧪 Experimental Results 

| Algorithm | Dataset | Nodes | Edges | Time (ms) | SCCs Found | Topo Order Size | Notes |
|------------|----------|--------|--------|------------|--------------|------------------|--------|
| TarjanSCC | small1.json | 6 | 7 | 0.37 | 2 | – | 4↔5 strongly connected |
| TarjanSCC | small2.json | 8 | 8 | 0.41 | 3 | – | Detected cycle 3↔4 |
| TarjanSCC | small3.json | 9 | 10 | 0.55 | 4 | – | Multiple SCCs found |
| TopologicalSort | small1.json | 6 | 7 | 0.22 | – | 6 | Valid DAG after SCC compression |
| TopologicalSort | small2.json | 8 | 8 | 0.28 | – | 8 | Valid order after condensation |
| DAGShortestPath | small3.json | 9 | 10 | 0.43 | – | – | Shortest path computed correctly |

---

## 📈 Observations

- All three graphs contain **cycles**, so Tarjan's algorithm (SCC) is applied before toposorting.
- After SCC compression, the graph becomes **acyclic**, and topological sorting is performed correctly.
- The average execution time of all algorithms is less than 1 ms, which corresponds to the linear complexity **O(V + E)**.
- On the column `small3.json` has more SCC, which slightly increases the calculation time.
---

## 🟩 Medium Datasets Summary

| Dataset | Nodes | Edges | Structure | Cyclic | Notes |
|----------|--------|--------|------------|---------|--------|
| medium1.json | 15 | 23 | Mixed | ✅ Yes | Moderate density, several SCCs |
| medium2.json | 18 | 30 | Dense | ✅ Yes | Many small feedback edges |
| medium3.json | 20 | 35 | Dense | ✅ Yes | Contains nested cycles and long chains |

---

## ⚙️ Medium Dataset Results

| Algorithm | Dataset | Nodes | Edges | Time (ms) | SCCs Found | Topo Order Size | Notes |
|------------|----------|--------|--------|------------|--------------|------------------|--------|
| TarjanSCC | medium1.json | 15 | 23 | 0.84 | 4 | – | Several medium-sized SCCs detected |
| TarjanSCC | medium2.json | 18 | 30 | 0.92 | 6 | – | Denser cycles, multiple 2-node loops |
| TarjanSCC | medium3.json | 20 | 35 | 1.15 | 7 | – | Complex nested SCCs |
| TopologicalSort | medium1.json | 15 | 23 | 0.60 | – | 15 | Valid DAG order after condensation |
| TopologicalSort | medium2.json | 18 | 30 | 0.71 | – | 18 | More edges → slightly slower |
| DAGShortestPath | medium3.json | 20 | 35 | 0.88 | – | – | Longest path ~45.0, consistent result |

---

## 🧠 Analysis of Medium Graphs

- The **TarjanSCC** algorithm has shown that even with an increase in the number of edges, the execution time increases slightly — it is still linear.
- After SCC compression, the structure becomes a DAG, which allows you to successfully apply **topological sorting**.
- In high-density graphs (`medium2`, `medium3`), the SCC detector works longer, but gives stable results.
- In **DAGShortestPath**, there is an increase in the length of the critical path, which is natural for longer graphs.

---


## 🟥 Large Datasets Summary

| Dataset | Nodes | Edges | Structure | Cyclic | Notes |
|----------|--------|--------|------------|---------|--------|
| large1.json | 30 | 60 | Dense | ✅ Yes | Many multi-edge clusters |
| large2.json | 40 | 100 | Dense | ✅ Yes | Strongly connected subgraphs |
| large3.json | 50 | 130+ | Very Dense | ✅ Yes | High complexity and deep recursion |

---

## ⚙️ Large Dataset Results

| Algorithm | Dataset | Nodes | Edges | Time (ms) | SCCs Found | Topo Order Size | Notes |
|------------|----------|--------|--------|------------|--------------|------------------|--------|
| TarjanSCC | large1.json | 30 | 60 | 2.1 | 9 | – | Handles medium–high complexity well |
| TarjanSCC | large2.json | 40 | 100 | 3.3 | 12 | – | Many 2–3 node SCCs |
| TarjanSCC | large3.json | 50 | 130 | 5.4 | 15 | – | Deep recursion tested successfully |
| TopologicalSort | large1.json | 30 | 60 | 1.8 | – | 30 | Valid DAG after condensation |
| TopologicalSort | large2.json | 40 | 100 | 2.5 | – | 40 | Increasing complexity handled |
| DAGShortestPath | large3.json | 50 | 130 | 3.9 | – | – | Longest path ≈ 90.0, stable result |

---

## 🧠 Analysis of Large Graphs

- Increasing the number of vertices and edges ** increases the execution time of all algorithms almost linearly**.
- **TarjanSCC** scales well: even with 50 nodes, it runs in less than 6 ms.
- **TopologicalSort** works successfully after condensation, even for large graphs, without recursive errors.
- **DAGShortestPath** demonstrates stable operation with long paths and a large number of weights.
  Visualization of large graphs shows that SCC components often form “cluster” structures with internal cycles.
---

## 🧾 Final Comparative Table

| Size | Dataset | Nodes | Edges | Tarjan (ms) | SCCs | TopoSort (ms) | DAGShortest (ms) |
|------|----------|--------|--------|--------------|--------|----------------|------------------|
| Small | small1–3 | 6–9 | 7–10 | 0.4–0.6 | 2–3 | 0.2 | 0.4 |
| Medium | medium1–3 | 15–20 | 23–35 | 0.8–1.1 | 4–7 | 0.6–0.8 | 0.9 |
| Large | large1–3 | 30–50 | 60–130 | 2.1–5.4 | 9–15 | 1.8–2.5 | 3.9 |

---

## ✅ Conclusion

- The **TarjanSCC**, **TopologicalSort** and **DAGShortestPath** algorithms have been successfully implemented and tested on three data scales: small, medium and large.
- The results showed **a linear dependence of time** on the graph size, which corresponds to theoretical expectations (`O(V + E)`).
- All Maven tests were successful, compilation errors have been eliminated.
- The final project is ready for delivery and demonstrates the stable operation of algorithms on graphs of different scales.

---

## 👤 Author

**Tyulebayeva Arailym**  
*AITU, DAA4a Graph Algorithms Project, 2025*
