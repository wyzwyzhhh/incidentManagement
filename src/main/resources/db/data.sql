-- Insert sample incidents
INSERT INTO incidents (title, description, status, priority) VALUES
('Server Down', 'Production server is not responding', 'OPEN', 'CRITICAL'),
('Database Slow', 'Database queries taking longer than usual', 'IN_PROGRESS', 'HIGH'),
('UI Bug', 'Button not working in Chrome browser', 'OPEN', 'MEDIUM'),
('Security Update', 'Need to update security patches', 'OPEN', 'HIGH'),
('Network Issue', 'Intermittent network connectivity', 'RESOLVED', 'HIGH'); 