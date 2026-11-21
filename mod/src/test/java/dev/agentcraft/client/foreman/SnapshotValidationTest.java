package dev.agentcraft.client.foreman;

import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.agentcraft.client.foreman.Protocol.Agent;
import dev.agentcraft.client.foreman.Protocol.Decision;
import dev.agentcraft.client.foreman.Protocol.Snapshot;
import dev.agentcraft.client.foreman.Protocol.Task;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Health check degli snapshot Foreman: id duplicati e riferimenti a entità assenti. */
class SnapshotValidationTest {
	private static Agent agent(String id) {
		return new Agent(id, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null);
	}

	private static Task task(String id, String assignee, List<String> deps) {
		return new Task(id, null, null, null, assignee, deps, null, null, 0, null,
			null, null, null, null, null, 0, 0);
	}

	private static Decision decision(String id, String agentId, String taskId) {
		return new Decision(id, agentId, null, null, null, null, null, null, taskId, null, null, null, 0);
	}

	private static Snapshot snapshot(List<Agent> agents, List<Task> tasks, List<Decision> decisions) {
		return new Snapshot(null, agents, tasks, decisions, List.of(), List.of(),
			null, List.of(), List.of(), List.of());
	}

	@Test
	void consistentSnapshotHasNoProblems() {
		Snapshot s = snapshot(List.of(agent("a")), List.of(task("t1", "a", List.of())),
			List.of(decision("d1", "a", "t1")));
		assertTrue(s.validate().isEmpty(), s.validate().toString());
	}

	@Test
	void reportsDuplicateIdsAndBrokenReferences() {
		Snapshot s = snapshot(List.of(agent("a"), agent("a")),
			List.of(task("t1", "ghost", List.of("missing"))),
			List.of(decision("d1", "ghost", "t2")));
		List<String> problems = s.validate();
		assertTrue(problems.stream().anyMatch(p -> p.contains("duplicate agent: a")), problems.toString());
		assertTrue(problems.stream().anyMatch(p -> p.contains("unknown assignee ghost")), problems.toString());
		assertTrue(problems.stream().anyMatch(p -> p.contains("unknown dependency missing")), problems.toString());
		assertTrue(problems.stream().anyMatch(p -> p.contains("unknown agent ghost")), problems.toString());
		assertTrue(problems.stream().anyMatch(p -> p.contains("unknown task t2")), problems.toString());
	}

	@Test
	void nullSnapshotListsAreNormalised() {
		Snapshot s = new Snapshot(null, null, null, null, null, null, null, null, null, null);
		assertTrue(s.validate().isEmpty(), s.validate().toString());
	}
}
