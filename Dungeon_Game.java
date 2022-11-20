package advancePrograming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Dungeon_Game {
	Scanner scanner = new Scanner(System.in);
	Set<int[]> visited = new HashSet<>();
	Queue<int[]> queue = new LinkedList<>();
	ArrayList<ArrayList<Integer>> pits = new ArrayList<>();
	int pathCount = 0;

	public static void main(String[] args) {
		Dungeon_Game player = new Dungeon_Game();
		player.gameStarts();
	}

	public void gameStarts() {

		System.out.println("Enter the row size of Dungeon : ");
		int n = scanner.nextInt();
		System.out.println("Enter the col size of Dungeon : ");
		int m = scanner.nextInt();
		boolean[][] dungeon = new boolean[n][m];

		System.out.println("Enter the postion of Adventurer : ");
		int[] adventurer = new int[2];
		adventurer[0] = scanner.nextInt();
		adventurer[1] = scanner.nextInt();

		System.out.println("Enter the postion of Gold : ");
		int[] gold = new int[2];
		gold[0] = scanner.nextInt();
		gold[1] = scanner.nextInt();

		System.out.println("Enter the postion of Monster : ");
		int[] mons = new int[2];
		mons[0] = scanner.nextInt();
		mons[1] = scanner.nextInt();

		System.out.println("Enter the postion of Trigger : ");
		int[] trigger = new int[2];
		trigger[0] = scanner.nextInt();
		trigger[1] = scanner.nextInt();

		System.out.println("Enter the number of pits :");
		int pitsCount = scanner.nextInt();
		for (int i = 0; i < pitsCount; i++) {
			ArrayList<Integer> pit = new ArrayList<>();
			pit.add(scanner.nextInt());
			pit.add(scanner.nextInt());
			pits.add(pit);
		}

		// adventurer
		getPathBFS(dungeon, adventurer, gold, true);
		int advenPath = shortestPath(dungeon, gold, true);
		visited.clear();
		queue.clear();
		pathCount = 0;
		for (boolean[] row : dungeon) {
			Arrays.fill(row, false);
		}

		// Monster
		getPathBFS(dungeon, mons, gold, false);
		int monsterPath = shortestPath(dungeon, mons, false);

		if (advenPath == -1) {
			System.out.println("No Possible Solution");
			return;
		}

		if (monsterPath < advenPath) {
			visited.clear();
			queue.clear();
			pathCount = 0;
			for (boolean[] row : dungeon) {
				Arrays.fill(row, false);
			}
			getPathBFS(dungeon, adventurer, trigger, true);
			int advenPathToTigger = shortestPath(dungeon, trigger, true);
			visited.clear();
			queue.clear();
			pathCount = 0;
			for (boolean[] row : dungeon) {
				Arrays.fill(row, false);
			}
			getPathBFS(dungeon, trigger, gold, true);
			advenPathToTigger += shortestPath(dungeon, gold, true);
			System.out.println(advenPathToTigger);
			return;
		}
		System.out.println(advenPath);

	}

	public int shortestPath(boolean[][] dungeon, int[] gold, boolean flag) {
		int size = queue.size();
		while (!queue.isEmpty()) {
			pathCount++;
			size = queue.size();
			for (int iter = 0; iter < size && !queue.isEmpty(); iter++) {
				if (getPathBFS(dungeon, queue.poll(), gold, flag)) {
					return pathCount;
				}
			}
		}
		return -1;
	}

	public boolean getPathBFS(boolean[][] dungeon, int[] adven, int[] gold, boolean flag) {
		if (adven[0] < 0 || adven[1] < 0 || adven[0] >= dungeon.length || adven[1] >= dungeon[0].length) {
			return false;
		}
		if (adven[0] == gold[0] && adven[1] == gold[1]) {
			return true;
		}
		ArrayList<Integer> isPit = new ArrayList<>();
		isPit.add(adven[0]);
		isPit.add(adven[1]);

		if (flag && pits.contains(isPit)) {
			return false;
		}

		dungeon[adven[0]][adven[1]] = true;
		// right
		if (adven[1] < dungeon[0].length - 1 && !dungeon[adven[0]][adven[1] + 1]) {
			queue.add(new int[] { adven[0], adven[1] + 1 });
		}
		// Up
		if (adven[0] > 0 && !dungeon[adven[0] - 1][adven[1]]) {
			queue.add(new int[] { adven[0] - 1, adven[1] });
		}
		// Left
		if (adven[1] > 0 && !dungeon[adven[0]][adven[1] - 1]) {
			queue.add(new int[] { adven[0], adven[1] - 1 });
		}
		// Down
		if (adven[0] < dungeon.length - 1 && !dungeon[adven[0] + 1][adven[1]]) {
			queue.add(new int[] { adven[0] + 1, adven[1] });
		}
		return false;
	}

}
