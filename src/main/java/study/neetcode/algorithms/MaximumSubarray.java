package study.neetcode.algorithms;

public class MaximumSubarray {

    public MaximumSubarrayResult divideAndConquer(int[] A) {
        if(A.length==0) return new MaximumSubarrayResult(null, null, null);
        return maximumSubarray(0,A.length-1,A);
    }

    private MaximumSubarrayResult maximumSubarray(int low, int high, int[] A) {
        System.out.println(low+ " "+high+ " "+A);
        if(low>=high) return new MaximumSubarrayResult(low,low,A[low]);
        else{
            int mid = (low+high)/2;
            MaximumSubarrayResult leftMax = maximumSubarray(low,mid-1,A);
            MaximumSubarrayResult rightMax = maximumSubarray(mid+1,high,A);
            MaximumSubarrayResult crossMax = maximumSubarrayCross(low,mid,high,A);
            MaximumSubarrayResult max = leftMax;
            if(max.compareTo(rightMax)<0) max = rightMax;
            if(max.compareTo(crossMax)<0) max = crossMax;
            return max;
        }
    }

    private MaximumSubarrayResult maximumSubarrayCross(int low, int mid, int high, int[] A) {
        System.out.println("cross - "+low+ " "+high+ " "+A);
        if(low>=high) return new MaximumSubarrayResult(low,low,A[low]);
        else {
            Integer leftCrossMax= Integer.MIN_VALUE;
            int leftIndex = mid;
            int rightIndex = mid;
            int currentSum =0;
            for(int i=mid;i>=0;i--){
                currentSum+=A[i];
                if(currentSum>leftCrossMax) {
                    leftCrossMax=currentSum;
                    leftIndex=i;
                }
            }
            currentSum=0;
            Integer rightCrossMax= Integer.MIN_VALUE;
            for(int i=mid+1;i<=high;i++){
                currentSum+=A[i];
                if(currentSum>rightCrossMax) {
                    rightCrossMax=currentSum;
                    rightIndex=i;
                }
            }
            return new MaximumSubarrayResult(leftIndex,rightIndex,leftCrossMax+rightCrossMax);
        }
    }

    record MaximumSubarrayResult(Integer left, Integer right, Integer max){
        public int compareTo(MaximumSubarrayResult rec){
            return this.max - rec.max;
        }
    }
}
