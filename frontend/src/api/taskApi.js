const BASE_URL = 'http://localhost:8080/api/v1/tasks';

// 1. 전체 태스크 목록 조회
export async function getTasks() {
    const response = await fetch(BASE_URL);
    if (!response.ok) {
        throw new Error(`HTTP 에러 발생: ${response.status}`);
    }
    // 백엔드의 공통 응답 규격 ApiResponse { success, data, error } 파싱
    const json = await response.json();
    return json.data;
}

// 2. 신규 태스크 생성
export async function createTask(title, description) {
    const response = await fetch(BASE_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title, description })
    });

    const json = await response.json();

    // 백엔드에서 400 Bad Request 등이 내려왔을 때
    if (!response.ok || !json.success) {
        throw new Error(json.error ? json.error.message : '태스크 생성 실패');
    }
    return json.data;
}

// 3. 태스크 상태 변경 (PATCH)
export async function updateTaskStatus(id, status) {
    const response = await fetch(`${BASE_URL}/${id}/status`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status })
    });
    const json = await response.json();
    if (!response.ok || !json.success) {
        throw new Error(json.error ? json.error.message : '상태 변경 실패');
    }
    return json.data;
}

// 4. 태스크 삭제 (DELETE)
export async function deleteTask(id) {
    const response = await fetch(`${BASE_URL}/${id}`, {
        method: 'DELETE'
    });
    if (!response.ok) {
        throw new Error('태스크 삭제 실패');
    }
    // 204 No Content는 본문이 없으므로 true 반환
    return true;
}