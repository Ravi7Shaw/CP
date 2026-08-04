from itertools import accumulate
from bisect import bisect_right
from typing import List

class Solution:
    def countTasks(self, tasks: List[int], shifts: List[int]) -> List[int]:
        pre = list(accumulate(tasks))
        p = 0
        res = []
        n = len(tasks)

        for s in shifts:
            p += s
            res.append(n - bisect_right(pre, p))
            if p >= pre[-1]:
                p = 0

        return res