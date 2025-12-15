# Frontend — Construction Permit System (Enhanced)

This frontend uses React + Vite + Tailwind.
Location: `frontend/`

## Features added
- Tailwind CSS styling
- Real JWT login integration (expects POST /api/auth/login -> { token })
- File upload for building plans (multipart/form-data to POST /api/building-plans)
- Professional UI components and responsive layout
- Fallback "select user" for demo/testing (uses GET /api/users)

## Run locally

1.'run backend on intellj'
2. `cd frontend`
3. `npm install`
4. `npm run dev`

## Notes
- The frontend expects the backend API under the same origin at `/api/*`. If your backend is at another host (e.g., http://localhost:8080), set env var `VITE_API_BASE=http://localhost:8080` before starting:
  - Linux/macOS: `VITE_API_BASE=http://localhost:8080 npm run dev`
  - Windows (PowerShell): `$env:VITE_API_BASE='http://localhost:8080'; npm run dev`
- If your backend does not implement `/api/auth/login`, use the fallback user selector to test features.
