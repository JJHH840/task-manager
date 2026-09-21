import { useState } from "react";

// 부모(App)로부터 'onCreate'라는 함수를 선물(Props)로 받음!
function TaskForm({ onCreate }) {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!title.trim()) {
            alert('태스크 제목을 입력해주세요!');
            return;
        }
        // 부모가 준 함수를 호출해서 데이터 전달!
        onCreate(title, description);
        // 입력창 싹 비우기
        setTitle('');
        setDescription('');
    };

    return (
        <form onSubmit={handleSubmit} style={{ display: 'flex', gap: '10px', marginBottom: '30px' }}>
            <input
                type="text"
                placeholder="태스크 제목 (필수)"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                style={{ flex: 2, padding: '10px', border: '1px solid #ccc', borderRadius: '4px' }}
            />
            <input
                type="text"
                placeholder="상세 설명"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                style={{ flex: 3, padding: '10px', border: '1px solid #ccc', borderRadius: '4px' }}
            />
            <button
                type="submit"
                style={{
                    padding: '10px 20px', cursor: 'pointer', background: '#2563eb',
                    color: 'white', border: 'none', borderRadius: '4px', fontWeight: 'bold'
                }}
            >
                등록
            </button>
        </form>

    )
}

export default TaskForm;