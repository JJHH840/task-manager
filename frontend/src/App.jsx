import { useEffect, useState } from 'react';
import { getTasks, createTask, updateTaskStatus, deleteTask } from './api/taskApi';
import './App.css';
import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';

function App() {
  // ★★★ 실무 핵심: 비동기 3대 상태 격리 ★★★
  const [tasks, setTasks] = useState([]);           // 1. 데이터 (Success)
  const [isLoading, setIsLoading] = useState(true); // 2. 로딩 중 (Loading)
  const [error, setError] = useState(null);        // 3. 에러 발생 (Error)

  // 1. 태스크 목록 불러오기 (3대 상태 적용)
  const loadTasks = async () => {
    setIsLoading(true);
    setError(null);
    try {
      const data = await getTasks();
      setTasks(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    loadTasks();
  }, []);

  // 2. 신규 등록
  const handleCreate = async (title, description) => {
    try {
      await createTask(title, description);
      loadTasks(); // 목록 새로고침
    } catch (err) {
      alert(`[생성 실패] ${err.message}`);
    }
  };

  // 3. 상태 변경 (TODO -> IN_PROGRESS -> DONE)
  const handleStatusChange = async (id, nextStatus) => {
    try {
      await updateTaskStatus(id, nextStatus);
      loadTasks();
    } catch (err) {
      alert(`[상태 변경 실패] ${err.message}`);
    }
  };

  // 4. 삭제
  const handleDelete = async (id) => {
    if (!window.confirm('정말 삭제하시겠습니까?')) return;
    try {
      await deleteTask(id);
      loadTasks();
    } catch (err) {
      alert(`[삭제 실패] ${err.message}`);
    }
  };

  return (
    <div style={{ maxWidth: '850px', margin: '40px auto', fontFamily: 'system-ui, sans-serif', padding: '20px' }}>
      <h1>🚀 엔터프라이즈 태스크 매니저</h1>

      {/* 부품 1: 입력 폼*/}
      <TaskForm onCreate={handleCreate} />

      {/* 비동기 3대 상태 렌더링 */}
      {isLoading && (
        <div style={{ textAlign: 'center', padding: '30px', color: '#64748b' }}>
          ⏳서버에서 태스크 목록을 불러오는 중입니다...
        </div>
      )}

      {error && (
        <div style={{ background: '#fee2e2', color: '#dc2626', padding: '15px', borderRadius: '6px', marginBottom: '20px' }}>
          ⚠️ 서버 통신 에러: {error}
        </div>
      )}

      {/* 부품 2: 태스크 목록 테이블 */}
      {!isLoading && !error && (
        <TaskList
          tasks={tasks}
          onStatusChange={handleStatusChange}
          onDelete={handleDelete}
        />
      )}
    </div>
  );
}

export default App;
