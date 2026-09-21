import TaskItem from "./TaskItem";

function TaskList({ tasks, onStatusChange, onDelete }) {
    // 실무 팁: 데이터가 0개일 때 안내 문구를 띄우는 Empty State UX!
    if (tasks.length === 0) {
        return (
            <div style={{ textAlign: 'center', padding: '40px', color: '#94a3b8', background: '#f8fafc', borderRadius: '8px' }}>
                🎉등록된 태스크가 없습니다! 새로운 태스크를 등록해보세요.
            </div>
        );
    }

    return (
        <table style={{ width: '100%', borderCollapse: 'collapse', textAlign: 'left', background: 'white', boxShadow: '0 1px 3px rgba(0,0,0,0.1)' }}>
            <thead>
                <tr style={{ borderBottom: '2px solid #e2e8f0', background: '#f8fafc' }}>
                    <th style={{ padding: '12px' }}>ID</th>
                    <th style={{ padding: '12px' }}>제목</th>
                    <th style={{ padding: '12px' }}>설명</th>
                    <th style={{ padding: '12px' }}>상태</th>
                    <th style={{ padding: '12px' }}>생성일시</th>
                    <th style={{ padding: '12px' }}>관리</th>
                </tr>
            </thead>
            <tbody>
                {tasks.map((task) => (
                    <TaskItem
                        key={task.id}
                        task={task}
                        onStatusChange={onStatusChange}
                        onDelete={onDelete}
                    />
                ))}
            </tbody>
        </table>
    );
}

export default TaskList;