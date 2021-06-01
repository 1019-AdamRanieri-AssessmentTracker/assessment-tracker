from daos.batch_dao import BatchDAO
from exceptions.resource_not_found import ResourceNotFound
from models.batch import Batch
from utils.db_connection import DbConn


class BatchDAOImpl(BatchDAO):

    def get_batch_by_id(self, batch_id):
        """Takes in an id for a batch record and returns a Batch object"""
        sql = "SELECT * FROM batches where id=%s"
        records = DbConn.make_connect(sql, [batch_id])
        if records:
            record = records[0]
            return Batch(id=record[0], start_date=record[1], end_date=record[2], name=record[3],
                         training_track=record[4])
        else:
            raise ResourceNotFound("No batch could be found with the given id")

    def get_all_batches_by_year(self, year):
        """Takes in a year and returns all the batches currently in progress for that year"""
        sql = "SELECT * FROM batches WHERE date_part('year', batches.start_date) = %s "
        records = DbConn.make_connect(sql, [year])
        batches = []
        for batch in records:
            batches.append(
                Batch(id=batch[0], start_date=batch[1], end_date=batch[2], name=batch[3], training_track=batch[4]))
        return batches
