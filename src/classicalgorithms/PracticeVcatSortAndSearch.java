package classicalgorithms;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 리스트 두 개 정렬 및 합병 + 이진탐색

// 시작점이 끝 점보다 작거나 같은동안 반복해야하는 포인트
// middle값 구할때 연산자 우선순위
// 퀵 정렬시에 파티셔닝 로직
// 마지막 리스트 합병 때 작은 값에 따른 해당 배열에서 값 불러들이기(혼동주의)
// 고오맙다!
public class PracticeVcatSortAndSearch {

    public static int[] arrA = {7, 4, 5, 2, 8, 10};
    public static int[] arrB = {777, 4, 1, 9, 43, 22, 88, 112, 3};
    static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        quickSort(arrA, 0, arrA.length - 1);
        quickSort(arrB, 0, arrB.length - 1);

        int[] result = merge(arrA, arrB);

        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();

        int target;
        do {
            System.out.print("찾으려는 값 입력: ");
            target = Integer.parseInt(br.readLine());
            if (target < 0) break;
            int ans = binarySearch(result, target);

            if (ans < 0) {
                System.out.println("찾으려는값 없음.");
            } else {
                System.out.println(ans);
                System.out.println("찾기시도 횟수: " + count);
            }
        } while (true);
    }

    private static int binarySearch(int[] a, int target) {

        count = 0;
        int start = 0;
        int end = a.length;
        int middle;

        while (start <= end) {
            // 시작점이 끝점보다 작은동안만 반복하게 되면 마지막에 Middle에 있는 값을 반환하지 못함
            middle = (start + end) / 2; // 연산자 우선순위 미친...
            count++;
            if (a[middle] == target) {
                return target;
            }

            if (a[middle] < target) start = middle + 1;
            else if (a[middle] > target) end = middle - 1;
        }
        return -1; //  찾지 못한경우 음수 반환
    }

    // 왼쪽 피봇 선택 재귀방식의 퀵정렬
    // 정렬의 이진탐색 방식같다는 느낌.
    // 정렬 배열의 크기가 커지면 한계가 있다는데, 이걸 중앙 피봇값 방식으로 해결한다고 함.
    public static void quickSort(int[] a, int low, int high) {

        // 재귀 종료조건
        if (low >= high) return;

        int piv = partition(a, low, high);
        quickSort(a, low, piv - 1);
        quickSort(a, piv + 1, high);

    }

    // 왼쪽 오른쪽 파티셔닝, 피봇 인덱스의 선택 단계
    private static int partition(int[] a, int low, int high) {

        int pivot = low; // 왼쪽을 피봇으로 선택
        // low++; // 피벗의 다음 칸부터 비교 시작 하는게 문제점임. 피봇 인덱스 위치의 데이터까지 고려해서 정렬해야함.
        while (low < high) {
            while (a[low] < a[pivot] && low < high) low++; //왼쪽에서는 피벗보다 큰 값을 선택
            while (a[high] > a[pivot] && low < high) high--; //으론쪽에서는 피벗보다 작은값을 선택
            // 반복문이 깨졌다면 인덱스를 찾은 것 이므로 교환
            swap(a, low, high);
        }
        swap(a, pivot, low); // A -> B change
        return low; // 위의 반복문이 깨진거면, low, high가 같은위치에 있는것, 왼쪽 인덱스인 low를 다시 피봇인덱스로 설정.
    }

    // 값을 단순히 변경해주는 역할을 하는 함수
    private static void swap(int[] a, int low, int high) {
        int temp = a[low];
        a[low] = a[high];
        a[high] = temp;
    }

    public static int[] merge(int[] a, int[] b) {

        int[] result = new int[a.length + b.length];

        // a, b, merge(result)배열에 대한 각각의 인덱스
        int ia = 0;
        int ib = 0;
        int ir = 0;

        while (ia < a.length && ib < b.length) {

            // 작은값을 합병배열에 대입하고 다음 인덱스로 넘어가기 위한 반복문들. 인덱스 혼동 주의
            if (a[ia] < b[ib]) {
                result[ir] = a[ia];
                ia++;
                ir++;
            } else if (a[ia] > b[ib]) {
                result[ir] = b[ib];
                ib++;
                ir++;
            } else { // 같은 데이터의 경우, 임의로 a배열의 원소를 먼저 대입
                result[ir] = a[ia];
                ia++;
                ir++;
            }
        }

        // a배열에 남은 값들이 존재한 경우(b배열이 비어서 반복이 깨진경우)
        if (ia < a.length) {

            while (ia < a.length) {
                result[ir] = arrA[ia];
                ia++;
                ir++;
            }
        } else { // 위의 반대
            while (ib < b.length) {
                result[ir] = arrB[ib];
                ib++;
                ir++;
            }
        }
        return result;
    }
}