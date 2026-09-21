// 부모로부터 task 1개와 상태변경, 삭제 함수를 선물(Props)로 받음!
function TaskItem({ task, onStatusChange, onDelete }) {
    return (
        <tr style={{ borderBottom: '1px solid #f1f5f9' }}>
            <td style={{ padding: '12px' }}>{task.id}</td>
            <td style={{ padding: '12px', fontWeight: 'bold' }}>{task.title}</td>
            <td style={{ padding: '12px', color: '#475569' }}>{task.description}</td>
            <td style={{ padding: '12px' }}>
                <span style={{
                    padding: '4px 8px', borderRadius: '4px', fontSize: '12px', fontWeight: 'bold',
                    background: task.status === 'DONE' ? '#dcfce7' :
                        task.status === 'IN_PROGRESS' ? '#fef9c3' : '#f1f5f9',
                    color: task.status === 'DONE' ? '#15803d' :
                        task.status === 'IN_PROGRESS' ? '#a16207' : '#475569'
                }}>
                    {task.status}
                </span>
            </td>
            <td style={{ padding: '12px', fontSize: '13px', color: '#64748b' }}>
                {task.createdAt ? new Date(task.createdAt).toLocaleDateString() : '_'}
            </td>
            <td style={{ padding: '12px', display: 'flex', gap: '6px' }}>
                {task.status !== 'DONE' && (
                    <button
                        onClick={() => onStatusChange(task.id, task.status === 'TODO' ? 'IN_PROGRESS' : 'DONE')}
                        style={{ padding: '6px 10px', cursor: 'pointer', fontSize: '12px', borderRadius: '4px', border: '1px solid #cbd5e1', background: '#fff' }}
                    >
                        {task.status === 'TODO' ? '진행하기' : '완료하기'}
                    </button>
                )}
                <button onClick={() => onDelete(task.id)}
                    style={{ padding: '6px 10px', cursor: 'pointer', fontSize: '12px', background: '#ef4444', color: 'white', border: 'none', borderRadius: '4px' }}
                >
                    삭제
                </button>
            </td>
        </tr>
    )
}

export default TaskItem;