import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Button,
  IconButton,
  Typography,
  TablePagination,
  Chip,
} from '@mui/material';
import { Edit as EditIcon, Delete as DeleteIcon, Add as AddIcon } from '@mui/icons-material';
import { getIncidents, deleteIncident } from '../services/api';

const IncidentList = () => {
  const navigate = useNavigate();
  const [incidents, setIncidents] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [totalElements, setTotalElements] = useState(0);

  const loadIncidents = async () => {
    try {
      const response = await getIncidents(page, rowsPerPage);
      setIncidents(response.data.content);
      setTotalElements(response.data.totalElements);
    } catch (error) {
      console.error('Error loading incidents:', error);
    }
  };

  useEffect(() => {
    loadIncidents();
  }, [page, rowsPerPage]);

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this incident?')) {
      try {
        await deleteIncident(id);
        loadIncidents();
      } catch (error) {
        console.error('Error deleting incident:', error);
      }
    }
  };

  const getPriorityColor = (priority) => {
    switch (priority) {
      case 'CRITICAL': return 'error';
      case 'HIGH': return 'warning';
      case 'MEDIUM': return 'info';
      case 'LOW': return 'success';
      default: return 'default';
    }
  };

  return (
    <Paper sx={{ width: '100%', overflow: 'hidden' }}>
      <Typography variant="h5" sx={{ p: 2 }}>
        Incidents
        <Button
          variant="contained"
          startIcon={<AddIcon />}
          sx={{ float: 'right' }}
          onClick={() => navigate('/create')}
        >
          Create New
        </Button>
      </Typography>
      <TableContainer>
        <Table stickyHeader>
          <TableHead>
            <TableRow>
              <TableCell>Title</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Priority</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {incidents.map((incident) => (
              <TableRow key={incident.id}>
                <TableCell>{incident.title}</TableCell>
                <TableCell>
                  <Chip label={incident.status} />
                </TableCell>
                <TableCell>
                  <Chip
                    label={incident.priority}
                    color={getPriorityColor(incident.priority)}
                  />
                </TableCell>
                <TableCell>
                  <IconButton onClick={() => navigate(`/edit/${incident.id}`)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton onClick={() => handleDelete(incident.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        component="div"
        count={totalElements}
        page={page}
        onPageChange={(e, newPage) => setPage(newPage)}
        rowsPerPage={rowsPerPage}
        onRowsPerPageChange={(e) => {
          setRowsPerPage(parseInt(e.target.value, 10));
          setPage(0);
        }}
      />
    </Paper>
  );
};

export default IncidentList; 