class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        mp = {}  # value -> index

        for i, num in enumerate(nums):
            diff = target - num

            if diff in mp:
                return [mp[diff], i]

            mp[num] = i