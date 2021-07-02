class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) return true;
        Map<Integer, List<Integer>> edges = new LinkedHashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            if (edges.containsKey(prerequisites[i][1])) {
                List<Integer> edgeNodes = edges.get(prerequisites[i][1]);
                edgeNodes.add(prerequisites[i][0]);
            } else {
                List<Integer> edgeNodes = new LinkedList<>();
                edgeNodes.add(prerequisites[i][0]);
                edges.put(prerequisites[i][1], edgeNodes);
            }
        }
        Set<Integer> visitedNodes = new HashSet<>();
        Set<Integer> recStackNodes = new HashSet<>();
        for (Integer startNode : edges.keySet()) {
            if (isCycle(edges, visitedNodes, recStackNodes, startNode)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCycle(Map<Integer, List<Integer>> edges, Set<Integer> visitedNodes, Set<Integer> recStackNodes
            , int startNode) {
        if (recStackNodes.contains(startNode)) return true;
        if (visitedNodes.contains(startNode)) return false;
        visitedNodes.add(startNode);
        recStackNodes.add(startNode);
        if (edges.containsKey(startNode)) {
            for (Integer nextNode : edges.get(startNode)) {
                if (isCycle(edges, visitedNodes, recStackNodes, nextNode)) return true;
            }
        }
        recStackNodes.remove(startNode);
        return false;
    }
}
