class MaxFrequencyStack {

    Map<Integer, Integer> frequency;
    Map<Integer, Stack<Integer>> group;
    int maxFrequency=0;
    public FreqStack() {
        frequency = new HashMap<>();
        group = new HashMap<>();
    }
    
    public void push(int val) {
        int f = frequency.getOrDefault(val,0) + 1;
        frequency.put(val, f);
        if(f > maxFrequency)
            maxFrequency = f;
        group.computeIfAbsent(f, z -> new Stack()).push(val);
    }
    
    public int pop() {
        int x = group.get(maxFrequency).pop();
        frequency.put(x, frequency.get(x)-1);
        if(group.get(maxFrequency).size() == 0) 
            maxFrequency--;
        return x;
    }
}
