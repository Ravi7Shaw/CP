class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

    
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

      
        Arrays.sort(freq);

        int pushes = 0;
        int pos = 0;

        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) continue;

            pushes += freq[i] * (pos / 8 + 1);
            pos++;
        }

        return pushes;
    }
}